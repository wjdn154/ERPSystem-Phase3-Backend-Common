package com.megazone.ERPSystem_phase3_FinanceHR.hr.service.basic_information_management.Position;


import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_information_management.employee.Position;

import java.util.Optional;

public interface PositionService {
    Optional<Position> getPositionById(Long id);
}
