package com.megazone.ERPSystem_phase3_Common.hr.controller.basic_configuration;

import com.megazone.ERPSystem_phase3_Common.hr.model.basic_configuration.dto.PositionSalaryStepSearchDTO;
import com.megazone.ERPSystem_phase3_Common.hr.model.basic_configuration.dto.PositionSalaryStepShowAllDTO;
import com.megazone.ERPSystem_phase3_Common.hr.service.basic_configuration.PositionSalaryStepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 직급별-호봉별-수당 금액 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hr")
public class PositionSalaryStepController {
    private final PositionSalaryStepService positionSalaryStepService;

    @PostMapping("basicconfiguration/positionsalarystep/show")
    public ResponseEntity<Object> show(@RequestBody Map<String, Long> positionId) {
        try{
            Long searchPositionId = positionId.get("positionId");
            PositionSalaryStepShowAllDTO result = positionSalaryStepService.show(searchPositionId);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("basicconfiguration/positionsalarystep/datecategoryshow")
    public ResponseEntity<Object> dateCategoryShow(@RequestBody PositionSalaryStepSearchDTO dto) {
        try {
            PositionSalaryStepShowAllDTO result = positionSalaryStepService.endDateShow(dto);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
