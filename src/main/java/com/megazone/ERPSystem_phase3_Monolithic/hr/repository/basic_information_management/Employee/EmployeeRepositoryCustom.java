package com.megazone.ERPSystem_phase3_Monolithic.hr.repository.basic_information_management.Employee;

import com.megazone.ERPSystem_phase3_Monolithic.hr.model.basic_information_management.employee.Employee;
import com.megazone.ERPSystem_phase3_Monolithic.hr.model.basic_information_management.employee.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeRepositoryCustom {
    List<Employee> findAllByUser();

}
