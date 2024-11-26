package com.megazone.ERPSystem_phase3_Common.hr.repository.basic_information_management.Employee;

import com.megazone.ERPSystem_phase3_Common.hr.model.basic_information_management.employee.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {
    List<Employee> findAllByUser();

}
