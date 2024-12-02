package com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto;

import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.enums.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentActivityEntryDTO {
    private String activityDescription;
    private ActivityType activityType;
}
