package com.megazone.ERPSystem_phase3_Common.hr.repository.attendance_management.LeavesType;

import com.megazone.ERPSystem_phase3_Common.hr.model.attendance_management.LeavesType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeavesTypeRepository extends JpaRepository<LeavesType, Long>, LeavesTypeRepositoryCustom {
}
