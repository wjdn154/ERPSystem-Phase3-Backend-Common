package com.megazone.ERPSystem_phase3_Common.common.user.service.basic_information_management.Users;

import com.megazone.ERPSystem_phase3_Common.common.user.model.basic_information_management.employee.Employee;
import com.megazone.ERPSystem_phase3_Common.common.user.model.basic_information_management.employee.dto.EmployeeDataDTO;
import com.megazone.ERPSystem_phase3_Common.common.user.model.basic_information_management.employee.dto.EmployeeShowToDTO;
import com.megazone.ERPSystem_phase3_Common.common.user.repository.basic_information_management.Employee.DepartmentRepository;
import com.megazone.ERPSystem_phase3_Common.common.user.repository.basic_information_management.Employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public void updateEmployee(EmployeeDataDTO employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getEmployeeId()).orElseThrow(
                () -> new RuntimeException("해당하는 사원정보가 없습니다."));

        employee.setDepartment(
                departmentRepository.findById(employeeDto.getDepartmentId()).orElseThrow(
                        () -> new RuntimeException("해당하는 부서가 없습니다.")));

        employee.setEmployeeNumber(employeeDto.getRegistrationNumber());
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employeeRepository.save(employee);
    }

    @Override
    public void saveEmployee(EmployeeShowToDTO employeeShowToDTO) {
        Employee employee = new Employee();

        employee.setDepartment(
                departmentRepository.findById(employeeShowToDTO.getDepartmentId()).orElseThrow(
                        () -> new RuntimeException("해당하는 부서가 없습니다.")));

        employee.setEmployeeNumber(employeeShowToDTO.getEmployeeNumber());
        employee.setEmail(employeeShowToDTO.getEmail());
        employee.setFirstName(employeeShowToDTO.getFirstName());
        employee.setLastName(employeeShowToDTO.getLastName());
        employeeRepository.save(employee);
    }
}
