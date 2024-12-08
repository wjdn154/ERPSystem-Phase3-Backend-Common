package com.megazone.ERPSystem_phase3_Common.common.company.service.basic_information_management.company;

import com.megazone.ERPSystem_phase3_Common.common.Integrated.model.dashboard.dto.IncomeStatementLedgerDashBoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

@Service
@Transactional
@RequiredArgsConstructor
public class FinancialServiceImpl implements FinancialService {
    private final RestClient financialServiceClient;

    @Override
    public IncomeStatementLedgerDashBoardDTO dashBoardShow() {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        try{
            IncomeStatementLedgerDashBoardDTO dto = financialServiceClient.post()
                .uri("ledger/incomeStatement/dashboardShow")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .body(IncomeStatementLedgerDashBoardDTO.class);
            return dto;
        }
        catch(Exception e) {
            throw new RuntimeException("회계 대시보드 차트 호출 실패");
        }
    }
}
