package com.megazone.ERPSystem_phase3_Common.hr.model.basic_configuration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionSalaryStepSearchDTO {
    private Long positionId;
    private LocalDate endMonth;
}

