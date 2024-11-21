package com.megazone.ERPSystem_phase3_FinanceHR.hr.controller.basic_configuration;

import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_configuration.dto.SalaryStepEntryDTO;
import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_configuration.dto.SalaryStepShowDTO;
import com.megazone.ERPSystem_phase3_FinanceHR.hr.service.basic_configuration.SalaryStepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hr")
public class SalaryStepController {
    private final SalaryStepService salaryStepService;

    /**
     * 호봉 과목 리스트 출력
     */
    @PostMapping("basicconfiguration/salarystep/show")
    public ResponseEntity<Object> show() {
        try{
            List<SalaryStepShowDTO> result = salaryStepService.show();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * 호봉 과목 리스트 추가
     */
    @PostMapping("basicconfiguration/salarystep/entry")
    public ResponseEntity<Object> entry(@RequestBody SalaryStepEntryDTO dto) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(salaryStepService.entry(dto));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
