package com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_information_management.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionShowDTO {
    private Long id;
    private String positionCode;
    private String positionName;
}
