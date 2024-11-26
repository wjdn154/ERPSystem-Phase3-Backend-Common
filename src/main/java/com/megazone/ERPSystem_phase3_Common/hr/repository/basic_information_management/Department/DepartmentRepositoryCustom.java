package com.megazone.ERPSystem_phase3_Common.hr.repository.basic_information_management.Department;

import com.megazone.ERPSystem_phase3_Common.hr.model.basic_information_management.employee.dto.DepartmentShowDTO;

import java.util.List;

public interface DepartmentRepositoryCustom {
    List<DepartmentShowDTO> findAllDepartments();
}
