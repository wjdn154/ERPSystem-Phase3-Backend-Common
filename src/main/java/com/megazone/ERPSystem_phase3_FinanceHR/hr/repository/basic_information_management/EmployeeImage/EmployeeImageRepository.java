package com.megazone.ERPSystem_phase3_FinanceHR.hr.repository.basic_information_management.EmployeeImage;

import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_information_management.employee.EmployeeImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeImageRepository extends JpaRepository<EmployeeImage, Long>, EmployeeImageRepositoryCustom {
}