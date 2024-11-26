package com.megazone.ERPSystem_phase3_Common.hr.model.basic_configuration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryStepShowAllDTO {
    private List<SalaryStepShowDTO> salaryStepShowDTOS;

    public static SalaryStepShowAllDTO create(List<SalaryStepShowDTO> step) {
        return new SalaryStepShowAllDTO(step);
    }
}
