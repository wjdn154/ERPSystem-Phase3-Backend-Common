package com.megazone.ERPSystem_phase3_Common.user.model.basic_information_management.employee.dto;

import com.megazone.ERPSystem_phase3_Common.user.model.basic_information_management.employee.enums.EmploymentStatus;
import com.megazone.ERPSystem_phase3_Common.user.model.basic_information_management.employee.enums.EmploymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeShowToDTO {
    private Long id;
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private String registrationNumber;
    private String phoneNumber;
    private EmploymentStatus employmentStatus;
    private EmploymentType employmentType;
    private String email;
    private String address;
    private LocalDate hireDate;
    private boolean isHouseholdHead;
    private String imagePath;


    private Long departmentId;
    private String departmentName;
    private String departmentCode;
    private String positionName;
    private String titleName;
    private Long bankAccountId;
    private String code;
    private String bankName;
    private String accountNumber;
}
