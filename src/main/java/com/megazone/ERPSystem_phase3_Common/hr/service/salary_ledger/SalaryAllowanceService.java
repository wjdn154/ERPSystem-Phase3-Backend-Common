package com.megazone.ERPSystem_phase3_Common.hr.service.salary_ledger;

import com.megazone.ERPSystem_phase3_Common.hr.model.salary_ledger.dto.SalaryLedgerAllowanceShowDTO;

import java.util.List;

public interface SalaryAllowanceService {
    List<SalaryLedgerAllowanceShowDTO> findSalaryAllowances(Long salaryId);
}
