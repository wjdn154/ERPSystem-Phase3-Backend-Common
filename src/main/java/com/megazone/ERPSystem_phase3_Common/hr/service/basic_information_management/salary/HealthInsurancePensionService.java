package com.megazone.ERPSystem_phase3_Common.hr.service.basic_information_management.salary;

import com.megazone.ERPSystem_phase3_Common.hr.model.basic_configuration.dto.HealthInsurancePensionShowDTO;
import com.megazone.ERPSystem_phase3_Common.hr.model.basic_configuration.dto.InsurancePensionCalculatorDTO;

import java.math.BigDecimal;
import java.util.List;

public interface HealthInsurancePensionService {
    BigDecimal calculator(InsurancePensionCalculatorDTO dto);
    BigDecimal calculator(BigDecimal amount);
    List<HealthInsurancePensionShowDTO> showAll();
}
