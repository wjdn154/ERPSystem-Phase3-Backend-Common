package com.megazone.ERPSystem_phase3_Common.hr.repository.basic_information_management.salary_ledger;

import com.megazone.ERPSystem_phase3_Common.hr.model.basic_information_management.salary.SalaryAllowance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryAllowanceRepository extends JpaRepository<SalaryAllowance, Long> ,SalaryAllowanceRepositoryCustom{
}
