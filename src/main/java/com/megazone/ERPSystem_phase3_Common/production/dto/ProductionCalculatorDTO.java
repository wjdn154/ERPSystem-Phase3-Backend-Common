package com.megazone.ERPSystem_phase3_Common.production.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionCalculatorDTO {
    private BigDecimal actualData;
    private BigDecimal averageData;
}
