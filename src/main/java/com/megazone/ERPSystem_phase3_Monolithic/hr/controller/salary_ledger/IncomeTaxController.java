package com.megazone.ERPSystem_phase3_Monolithic.hr.controller.salary_ledger;

import com.megazone.ERPSystem_phase3_Monolithic.hr.model.basic_information_management.salary.dto.IncomeTaxShowDTO;
import com.megazone.ERPSystem_phase3_Monolithic.hr.service.salary_ledger.IncomeTaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hr")
public class IncomeTaxController {
    private final IncomeTaxService incomeTaxService;

    @PostMapping("incometax/show")
    public ResponseEntity<Object> show() {
        try {
            List<IncomeTaxShowDTO> result = incomeTaxService.show();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
