package com.megazone.ERPSystem_phase3_Common.hr.service.attendance_management.Leaves;

import com.megazone.ERPSystem_phase3_Common.hr.model.attendance_management.dto.LeavesAllShowDTO;
import com.megazone.ERPSystem_phase3_Common.hr.model.attendance_management.dto.LeavesCreateDTO;
import com.megazone.ERPSystem_phase3_Common.hr.model.attendance_management.dto.LeavesShowDTO;

import java.util.List;

public interface LeavesService {

    LeavesShowDTO createLeave(LeavesCreateDTO dto);

    List<LeavesAllShowDTO> getLeavesList();
}
