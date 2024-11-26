package com.megazone.ERPSystem_phase3_Common.hr.model.basic_information_management.employee.dto;

import com.megazone.ERPSystem_phase3_Common.hr.model.basic_information_management.employee.Transfer;
import com.megazone.ERPSystem_phase3_Common.hr.model.basic_information_management.employee.enums.TransferType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferShowDTO {
    private Long id;
    private LocalDate transferDate;
    private Long employeeId;
    private String employeeNumber;
    private String employeeName; // 사원이름 (성 + 이름)
    private Long fromDepartmentId; // 출발 부서 ID
    private Long toDepartmentId; // 도착 부서 ID
    private String toDepartmentCode;
    private String fromDepartmentCode;
    private String fromDepartmentName;
    private String toDepartmentName;
    private String reason;
    private TransferType transferType;

    public static TransferShowDTO create(Transfer transfer) {
        return new TransferShowDTO(
                transfer.getId(),
                transfer.getTransferDate(),
                transfer.getEmployee().getId(),
                transfer.getEmployee().getEmployeeNumber(),
                transfer.getEmployee().getLastName() + transfer.getEmployee().getFirstName(),
                transfer.getFromDepartment().getId(),
                transfer.getToDepartment().getId(),
                transfer.getFromDepartment().getDepartmentCode(),
                transfer.getToDepartment().getDepartmentCode(),
                transfer.getFromDepartment().getDepartmentName(),
                transfer.getToDepartment().getDepartmentName(),
                transfer.getReason(),
                transfer.getTransferType()
        );
    }
}
