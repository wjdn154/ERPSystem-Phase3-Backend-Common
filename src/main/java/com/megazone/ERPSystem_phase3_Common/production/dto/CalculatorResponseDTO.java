package com.megazone.ERPSystem_phase3_Common.production.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatorResponseDTO {
    private Integer wasteScore;
    private Integer energyScore;
}