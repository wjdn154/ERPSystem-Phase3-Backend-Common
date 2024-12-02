package com.megazone.ERPSystem_phase3_Common.Integrated.service.dashboard;

import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.DashboardDataDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.EnvironmentalCertificationSaveDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.RecentActivityEntryDTO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IntegratedService {
    DashboardDataDTO dashboard();

    @Nullable
    @Transactional(readOnly = true)
    DashboardDataDTO.EnvironmentalScoreDTO getEnvironmentalScoreDTO();

    @NotNull List<DashboardDataDTO.ActivityDTO> getActivityDTOS();

    void recentActivitySave(RecentActivityEntryDTO requestData);

    void environmentalCertification(EnvironmentalCertificationSaveDTO dto);
}
