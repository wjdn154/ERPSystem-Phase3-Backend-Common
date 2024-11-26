package com.megazone.ERPSystem_phase3_FinanceHR.hr.repository.basic_information_management.salary_ledger;

import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.salary_ledger.SalaryLedger;
import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.salary_ledger.SalaryLedgerDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryLedgerRepository extends JpaRepository<SalaryLedger, Long> , SalaryLedgerRepositoryCustom{
    SalaryLedger findFirstBySalaryLedgerDate(SalaryLedgerDate salaryLedgerDate);
}