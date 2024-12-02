package com.megazone.ERPSystem_phase3_Common.production;

import com.megazone.ERPSystem_phase3_Common.production.dto.CalculatorDTO;
import com.megazone.ERPSystem_phase3_Common.production.dto.CalculatorResponseDTO;
import com.megazone.ERPSystem_phase3_Common.production.dto.ProductionCalculatorDTO;

import java.math.BigDecimal;

public interface ProductionService {
    Integer wasteScoreCalculator(ProductionCalculatorDTO requestData);

    Integer energyScoreCalculator(ProductionCalculatorDTO requestData);

    CalculatorResponseDTO ScoreAllCalculator(CalculatorDTO requestData);

    BigDecimal totalWorkPerformance();
}
