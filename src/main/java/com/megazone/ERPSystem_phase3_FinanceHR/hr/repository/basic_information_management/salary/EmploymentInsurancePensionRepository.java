package com.megazone.ERPSystem_phase3_FinanceHR.hr.repository.basic_information_management.salary;

import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_information_management.salary.EmploymentInsurancePension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmploymentInsurancePensionRepository extends JpaRepository<EmploymentInsurancePension, Long> , EmploymentInsurancePensionRepositoryCustom{
    Optional<EmploymentInsurancePension> findFirstByEndDateIsNull();
}
