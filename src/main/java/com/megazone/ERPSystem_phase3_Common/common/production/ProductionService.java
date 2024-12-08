package com.megazone.ERPSystem_phase3_Common.common.production;

import com.megazone.ERPSystem_phase3_Common.common.production.dto.CalculatorDTO;
import com.megazone.ERPSystem_phase3_Common.common.production.dto.CalculatorResponseDTO;
import com.megazone.ERPSystem_phase3_Common.common.production.dto.ProductionCalculatorDTO;

import java.math.BigDecimal;

public interface ProductionService {
    Integer wasteScoreCalculator(ProductionCalculatorDTO requestData);

    Integer energyScoreCalculator(ProductionCalculatorDTO requestData);

    CalculatorResponseDTO ScoreAllCalculator(CalculatorDTO requestData);

    BigDecimal totalWorkPerformance();
}
