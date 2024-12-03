package com.megazone.ERPSystem_phase3_Common.Integrated.controller.integrated;

import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.DashboardDataDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.EnvironmentalCertificationSaveDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.RecentActivityEntryDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.service.dashboard.IntegratedService;
//import com.megazone.ERPSystem_phase3_Monolithic.common.config.SecretManagerConfig;
import com.megazone.ERPSystem_phase3_Common.Integrated.service.dashboard.S3DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/common")
@RequiredArgsConstructor
public class IntegratedController {

    private final IntegratedService integratedService;
    private final S3DashboardService s3DashboardService;

    @PostMapping("/dashboard")
    public ResponseEntity<Object> dashboard() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(integratedService.dashboard());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/recentActivity/save")
    public ResponseEntity<Object> recentActivity(@RequestBody RecentActivityEntryDTO requestData) {
        try {
            integratedService.recentActivitySave(requestData);
            return ResponseEntity.status(HttpStatus.OK).body(requestData.getActivityDescription());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/environmental")
    public ResponseEntity<String> environmentalCertificationAssessmentSave(@RequestBody EnvironmentalCertificationSaveDTO dto) {
        try {
            integratedService.environmentalCertification(dto);
            return ResponseEntity.status(HttpStatus.OK).body("환경점수 저장 완료");
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}