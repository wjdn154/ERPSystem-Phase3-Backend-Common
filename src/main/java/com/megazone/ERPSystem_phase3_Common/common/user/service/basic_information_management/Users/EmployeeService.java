package com.megazone.ERPSystem_phase3_Common.common.user.service.basic_information_management.Users;

import com.megazone.ERPSystem_phase3_Common.common.user.model.basic_information_management.employee.dto.EmployeeDataDTO;
import com.megazone.ERPSystem_phase3_Common.common.user.model.basic_information_management.employee.dto.EmployeeShowToDTO;

public interface EmployeeService {
    void updateEmployee(EmployeeDataDTO employeeShowToDTO);

    void saveEmployee(EmployeeShowToDTO employeeShowToDTO);
}
