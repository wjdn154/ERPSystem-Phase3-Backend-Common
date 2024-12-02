//package com.megazone.ERPSystem_phase3_Common.common.config.batch;
//
//import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.DashboardDataDTO;
//import com.megazone.ERPSystem_phase3_Common.Integrated.service.dashboard.IntegratedService;
//import com.megazone.ERPSystem_phase3_Common.Integrated.service.dashboard.S3DashboardService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.StepExecution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class LoadDashboardFromS3 implements Tasklet {
//
//    private final IntegratedService integratedService;
//    private final S3DashboardService s3DashboardService;
//
//    @Override
//    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//        String s3Key = "dashboard-data.json"; // S3에서 가져올 데이터의 키
//
//        try {
//            // S3에서 데이터를 가져옴
//            DashboardDataDTO dashboardDataDTO = s3DashboardService.fetchDashboardData(s3Key);
//            log.info("S3에서 대시보드 데이터를 성공적으로 읽어왔습니다: {}", dashboardDataDTO);
//
//            // 가져온 데이터를 처리하는 로직 추가 가능
////            processDashboardData(dashboardDataDTO);
//
//        } catch (Exception e) {
//            log.error("S3에서 데이터를 읽어오는 도중 오류 발생: {}", e.getMessage());
//            throw e; // 오류 발생 시 예외를 다시 던져서 배치 실패 처리
//        }
//
//        return RepeatStatus.FINISHED;
//    }
//
//    private void processDashboardData(DashboardDataDTO dashboardDataDTO) {
//        // 대시보드 데이터를 활용한 추가 처리 로직
//        log.info("대시보드 데이터를 처리합니다: {}", dashboardDataDTO);
//        // 필요시, 데이터베이스 업데이트, 메모리 캐시 반영 등
//    }
//}
