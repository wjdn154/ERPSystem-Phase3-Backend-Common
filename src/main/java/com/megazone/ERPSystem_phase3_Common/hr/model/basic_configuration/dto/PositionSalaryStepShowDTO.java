package com.megazone.ERPSystem_phase3_Common.hr.model.basic_configuration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionSalaryStepShowDTO {
    private Long positionSalaryStepId;
    private Long salaryStepId;
    private String salaryStepName;
    private List<PositionSalaryStepAllowanceDetailDTO> allowances;
    private BigDecimal totalAllowance;
    private LocalDate startDate;
    private LocalDate endDate;

    public static PositionSalaryStepShowDTO create(PositionSalaryStepDTO dto) {
        return new PositionSalaryStepShowDTO(
                dto.getPositionSalaryStepId(),
                dto.getSalaryStepId(),
                dto.getSalaryStepName(),
                null,
                BigDecimal.ZERO,
                dto.getStartDate(),
                dto.getEndDate()
        );
    }
}
