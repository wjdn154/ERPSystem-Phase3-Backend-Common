package com.megazone.ERPSystem_phase3_Common.Integrated.service.dashboard;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.EnvironmentalCertificationAssessment;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.RecentActivity;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.DashboardDataDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.EnvironmentalCertificationSaveDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.IncomeStatementLedgerDashBoardDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.IncomeStatementLedgerShowDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.RecentActivityEntryDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.repository.dashboard.EnvironmentalCertificationAssessmentRepository;
import com.megazone.ERPSystem_phase3_Common.Integrated.repository.dashboard.RecentActivityRepository;
import com.megazone.ERPSystem_phase3_Common.common.config.redis.RedisCacheable;
import com.megazone.ERPSystem_phase3_Common.company.service.basic_information_management.company.FinancialService;
import com.megazone.ERPSystem_phase3_Common.logistics.LogisticService;
import com.megazone.ERPSystem_phase3_Common.production.ProductionService;
import com.megazone.ERPSystem_phase3_Common.production.dto.CalculatorDTO;
import com.megazone.ERPSystem_phase3_Common.production.dto.CalculatorResponseDTO;
import com.megazone.ERPSystem_phase3_Common.user.repository.basic_information_management.Employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class IntegratedServiceImpl implements IntegratedService {

    private final RecentActivityRepository recentActivityRepository;
    private final EnvironmentalCertificationAssessmentRepository environmentalCertificationAssessmentRepository;
    private final FinancialService financialService;
    private final ProductionService productionService;
    private final EmployeeRepository employeeRepository;
    private final LogisticService logisticService;

    @Override
    public DashboardDataDTO dashboard() {
        List<DashboardDataDTO.ActivityDTO> activities = getActivityDTOS(); // 최근 활동
        DashboardDataDTO.EnvironmentalScoreDTO environmentalScore = getEnvironmentalScoreDTO(); // 환경 점수
        IncomeStatementLedgerDashBoardDTO incomeStatementLedgerDashBoardDTO = financialService.dashBoardShow(); // 매출 및 비용 추이 데이터 집계
        List<DashboardDataDTO.SalesDataDTO> salesDataList = getSalesDataDTOS(incomeStatementLedgerDashBoardDTO); // 매출 및 비용 추이 데이터 가공
        DashboardDataDTO.DashboardWidgetDTO widgets = getDashboardWidgetDTO(incomeStatementLedgerDashBoardDTO); // 총매출, 총직원수, 재고현황, 생산량

        return DashboardDataDTO.builder()
                .widgets(widgets)
                .activities(activities)
                .environmentalScore(environmentalScore)
                .salesData(salesDataList)
                .build();
    }

    private DashboardDataDTO.DashboardWidgetDTO getDashboardWidgetDTO(IncomeStatementLedgerDashBoardDTO incomeStatementLedgerDashBoardDTO) {

        BigDecimal totalWorkPerformance = productionService.totalWorkPerformance(); // 총 생산량

        DashboardDataDTO.DashboardWidgetDTO widgets = DashboardDataDTO.DashboardWidgetDTO.builder()
                .financeName("총 매출")
                .financeValue(incomeStatementLedgerDashBoardDTO.getTotalRevenue())
                .productionName("총 생산량")
                .productionValue(totalWorkPerformance)
                .hrName("총 직원수")
                .logisticsName("총 재고 현황")
                .logisticsValue(logisticService.inventoryCount())
                .hrValue(Long.valueOf((employeeRepository.findAll().size())))
                .build();
        return widgets;
    }

    @NotNull
    private List<DashboardDataDTO.SalesDataDTO> getSalesDataDTOS(IncomeStatementLedgerDashBoardDTO incomeStatementLedgerDashBoardDTO) {
        AtomicInteger monthIndex = new AtomicInteger(1);
        List<DashboardDataDTO.SalesDataDTO> salesDataList = incomeStatementLedgerDashBoardDTO.getIncomeStatementLedger().stream()
                .map(incomeStatementLedgerMonthList -> {
                    BigDecimal sales = incomeStatementLedgerMonthList.stream()
                            .filter(item -> item.getName().equals("매출"))
                            .map(IncomeStatementLedgerShowDTO::getTotalAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    BigDecimal cost = incomeStatementLedgerMonthList.stream()
                            .filter(item -> item.getName().equals("비용"))
                            .map(IncomeStatementLedgerShowDTO::getTotalAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    return DashboardDataDTO.SalesDataDTO.builder()
                            .name(monthIndex.getAndIncrement() + "월")
                            .sales(sales)
                            .cost(cost)
                            .build();
                })
                .collect(Collectors.toList());
        return salesDataList;
    }

    @Nullable
    @Transactional(readOnly = true)
    @Override
    public DashboardDataDTO.EnvironmentalScoreDTO getEnvironmentalScoreDTO() {
        DashboardDataDTO.EnvironmentalScoreDTO environmentalScore = environmentalCertificationAssessmentRepository.findByMonth(YearMonth.now())
                .map(environmentalCertificationAssessment -> DashboardDataDTO.EnvironmentalScoreDTO.builder()
                        .totalScore(environmentalCertificationAssessment.getTotalScore())
                        .wasteScore(environmentalCertificationAssessment.getWasteScore())
                        .energyScore(environmentalCertificationAssessment.getEnergyScore())
                        .build())
                .orElse(null);
        return environmentalScore;
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<DashboardDataDTO.ActivityDTO> getActivityDTOS() {
        List<DashboardDataDTO.ActivityDTO> activities = recentActivityRepository.findAllByOrderByActivityTimeDesc()
                .stream()
                .map(activity -> DashboardDataDTO.ActivityDTO.builder()
                        .id(activity.getId())
                        .activityDescription(activity.getActivityDescription())
                        .activityType(activity.getActivityType())
                        .activityTime(calculateTimeAgo(activity.getActivityTime()))
                        .build())
                .toList();
        return activities;
    }

    private String calculateTimeAgo(LocalDateTime activityTime) {
        LocalDateTime now = LocalDateTime.now();
        long years = ChronoUnit.YEARS.between(activityTime, now);
        if (years > 0) return years + "년 전";

        long months = ChronoUnit.MONTHS.between(activityTime, now);
        if (months > 0) return months + "달 전";

        long weeks = ChronoUnit.WEEKS.between(activityTime, now);
        if (weeks > 0) return weeks + "주 전";

        long days = ChronoUnit.DAYS.between(activityTime, now);
        if (days > 0) return days + "일 전";

        long hours = ChronoUnit.HOURS.between(activityTime, now);
        if (hours > 0) return hours + "시간 전";

        long minutes = ChronoUnit.MINUTES.between(activityTime, now);
        if (minutes > 0) return minutes + "분 전";

        return "방금 전";
    }


    @Override
    public void recentActivitySave(RecentActivityEntryDTO requestData) {
        recentActivityRepository.save(
                RecentActivity.builder()
                        .activityDescription(requestData.getActivityDescription())
                        .activityType(requestData.getActivityType())
                        .activityTime(LocalDateTime.now())
                        .build());
    }

    @Override
    public void environmentalCertification(EnvironmentalCertificationSaveDTO dto) {
        Optional<EnvironmentalCertificationAssessment> ECA = environmentalCertificationAssessmentRepository.findByMonth(dto.getYearMonth());

        if (ECA.isEmpty()) {
            CalculatorResponseDTO scoreResponse = productionService.ScoreAllCalculator(
                    new CalculatorDTO(
                            dto.getActualEnergyConsumption(),
                            dto.getAverageWasteGenerated(),
                            dto.getActualEnergyConsumption(),
                            dto.getAverageEnergyConsumption()));

            Integer wasteScore = scoreResponse.getWasteScore();
            Integer energyScore = scoreResponse.getEnergyScore();

            Integer totalScore = (wasteScore + energyScore) / 2;

            EnvironmentalCertificationAssessment newECA = EnvironmentalCertificationAssessment.builder()
                    .month(dto.getYearMonth())
                    .totalWasteGenerated(dto.getActualWasteGenerated())
                    .totalEnergyConsumed(dto.getActualEnergyConsumption())
                    .totalIndustryAverageWasteGenerated(dto.getAverageWasteGenerated())
                    .totalIndustryAverageEnergyConsumed(dto.getAverageEnergyConsumption())
                    .wasteScore(wasteScore)
                    .energyScore(energyScore)
                    .totalScore(totalScore)
                    .createdDate(LocalDateTime.now())
                    .modifiedDate(LocalDateTime.now())
                    .build();
            environmentalCertificationAssessmentRepository.save(newECA);
        } else {
            EnvironmentalCertificationAssessment existingECA = ECA.get();
            BigDecimal totalWasteGenerated = existingECA.getTotalWasteGenerated().add(dto.getActualWasteGenerated());
            BigDecimal totalEnergyConsumed = existingECA.getTotalEnergyConsumed().add(dto.getActualEnergyConsumption());
            BigDecimal totalAverageWasteGenerated = existingECA.getTotalIndustryAverageWasteGenerated().add(dto.getAverageWasteGenerated());
            BigDecimal totalAverageEnergyConsumption = existingECA.getTotalIndustryAverageEnergyConsumed().add(dto.getAverageEnergyConsumption());


            CalculatorResponseDTO scoreResponse = productionService.ScoreAllCalculator(
                    new CalculatorDTO(
                            totalWasteGenerated,
                            totalAverageWasteGenerated,
                            totalEnergyConsumed,
                            totalAverageEnergyConsumption));

            Integer wasteScore = scoreResponse.getWasteScore();
            Integer energyScore = scoreResponse.getEnergyScore();
            Integer totalScore = (wasteScore + energyScore) / 2;

            existingECA.setTotalWasteGenerated(totalWasteGenerated);
            existingECA.setTotalEnergyConsumed(totalEnergyConsumed);
            existingECA.setTotalIndustryAverageWasteGenerated(totalAverageWasteGenerated);
            existingECA.setTotalIndustryAverageEnergyConsumed(totalAverageEnergyConsumption);
            existingECA.setWasteScore(wasteScore);
            existingECA.setEnergyScore(energyScore);
            existingECA.setTotalScore(totalScore);
            existingECA.setModifiedDate(LocalDateTime.now());

            environmentalCertificationAssessmentRepository.save(existingECA);
        }
    }
}