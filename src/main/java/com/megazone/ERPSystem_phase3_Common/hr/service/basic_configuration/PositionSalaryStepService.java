package com.megazone.ERPSystem_phase3_Common.hr.service.basic_configuration;

import com.megazone.ERPSystem_phase3_Common.hr.model.basic_configuration.dto.PositionSalaryStepSearchDTO;
import com.megazone.ERPSystem_phase3_Common.hr.model.basic_configuration.dto.PositionSalaryStepShowAllDTO;

public interface PositionSalaryStepService {
    PositionSalaryStepShowAllDTO show(Long positionId);

    PositionSalaryStepShowAllDTO endDateShow(PositionSalaryStepSearchDTO dto);
}
