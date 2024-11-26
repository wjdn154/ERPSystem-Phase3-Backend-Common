package com.megazone.ERPSystem_phase3_Common.hr.model.salary_ledger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryLedgerSearchDTO {
    private Long employeeId;
    private Long salaryLedgerDateId;
}
