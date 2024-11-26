package com.megazone.ERPSystem_phase3_Common.hr.service.salary_ledger;

import com.megazone.ERPSystem_phase3_Common.hr.model.basic_information_management.salary.IncomeTax;
import com.megazone.ERPSystem_phase3_Common.hr.model.basic_information_management.salary.dto.IncomeTaxShowDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IncomeTaxService {
    List<IncomeTax> showBaseIncomeTax();

    BigDecimal incomeTaxCalculator(BigDecimal amount);

    List<IncomeTaxShowDTO> show();
}
