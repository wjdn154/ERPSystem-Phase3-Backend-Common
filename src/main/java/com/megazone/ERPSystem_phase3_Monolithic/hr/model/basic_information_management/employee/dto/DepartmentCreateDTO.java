package com.megazone.ERPSystem_phase3_Monolithic.hr.model.basic_information_management.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentCreateDTO {
    private String departmentCode;
    private String departmentName;
    private String location;
}
