package com.megazone.ERPSystem_phase3_Common.production.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatorDTO {
    private BigDecimal wasteActualData;
    private BigDecimal wasteAverageData;
    private BigDecimal energyActualData;
    private BigDecimal energyAverageData;
}