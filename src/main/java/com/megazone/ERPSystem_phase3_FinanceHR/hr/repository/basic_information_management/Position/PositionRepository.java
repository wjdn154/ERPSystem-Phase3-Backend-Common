package com.megazone.ERPSystem_phase3_FinanceHR.hr.repository.basic_information_management.Position;

import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_information_management.employee.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {

    Optional<Position> findByPositionName(String name);

}
