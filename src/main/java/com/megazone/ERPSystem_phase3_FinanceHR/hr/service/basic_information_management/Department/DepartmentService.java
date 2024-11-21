package com.megazone.ERPSystem_phase3_FinanceHR.hr.service.basic_information_management.Department;


import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_information_management.employee.dto.DepartmentCreateDTO;
import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_information_management.employee.dto.DepartmentDetailDTO;
import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_information_management.employee.dto.DepartmentShowDTO;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    // 부서 리스트 조회
    List<DepartmentShowDTO> findAllDepartments();

    // 부서 id 로 조회
    Optional<DepartmentDetailDTO> findDepartmentById(Long id);

    // 부서 등록
    DepartmentCreateDTO saveDepartment(DepartmentCreateDTO dto);

    boolean hasEmployees(Long id);

    void deleteDepartment(Long id);

    DepartmentDetailDTO updateDepartment(Long id, DepartmentCreateDTO dto);
}
