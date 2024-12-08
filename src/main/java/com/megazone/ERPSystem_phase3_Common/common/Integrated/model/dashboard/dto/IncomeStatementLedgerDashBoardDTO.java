package com.megazone.ERPSystem_phase3_Common.common.Integrated.model.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeStatementLedgerDashBoardDTO {
    private List<List<IncomeStatementLedgerShowDTO>> incomeStatementLedger;
    private BigDecimal totalRevenue;
    private BigDecimal totalExpense;
}
