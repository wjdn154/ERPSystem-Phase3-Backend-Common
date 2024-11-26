package com.megazone.ERPSystem_phase3_Common.hr.model.basic_information_management.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDepartmentDTO {
    private Long id;
    private String employeeNumber;
    private String firstName;
    private String lastName;

    private String positionName;
    private String titleName;
}
