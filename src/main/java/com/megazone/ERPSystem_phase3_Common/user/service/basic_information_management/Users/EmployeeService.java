package com.megazone.ERPSystem_phase3_Common.user.service.basic_information_management.Users;

import com.megazone.ERPSystem_phase3_Common.user.model.basic_information_management.employee.dto.EmployeeShowToDTO;

public interface EmployeeService {
    void updateEmployee(EmployeeShowToDTO employeeShowToDTO);

    void saveEmployee(EmployeeShowToDTO employeeShowToDTO);
}
