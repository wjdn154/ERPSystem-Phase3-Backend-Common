package com.megazone.ERPSystem_phase3_Common.user.repository.basic_information_management.Employee;

import com.megazone.ERPSystem_phase3_Common.user.model.basic_information_management.employee.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
