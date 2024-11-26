package com.megazone.ERPSystem_phase3_Common.hr.service.basic_configuration;


import com.megazone.ERPSystem_phase3_Common.hr.model.basic_configuration.dto.SalaryStepEntryDTO;
import com.megazone.ERPSystem_phase3_Common.hr.model.basic_configuration.dto.SalaryStepShowDTO;

import java.util.List;

public interface SalaryStepService {
    List<SalaryStepShowDTO> show();

    String entry(SalaryStepEntryDTO dto);
}
