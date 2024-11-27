package com.megazone.ERPSystem_phase3_Common.Integrated.service.dashboard;

import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.DashboardDataDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.EnvironmentalCertificationSaveDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.RecentActivityEntryDTO;

public interface IntegratedService {
    DashboardDataDTO dashboard();

    void recentActivitySave(RecentActivityEntryDTO requestData);

    void environmentalCertification(EnvironmentalCertificationSaveDTO dto);
}
