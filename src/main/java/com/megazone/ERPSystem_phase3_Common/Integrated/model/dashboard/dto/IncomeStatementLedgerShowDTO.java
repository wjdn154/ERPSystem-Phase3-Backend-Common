package com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeStatementLedgerShowDTO {
    private String level;
    private String name;
    private BigDecimal totalAmount;
}
