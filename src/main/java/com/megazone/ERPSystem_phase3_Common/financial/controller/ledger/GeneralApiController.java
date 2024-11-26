package com.megazone.ERPSystem_phase3_Common.financial.controller.ledger;

import com.megazone.ERPSystem_phase3_Common.financial.model.ledger.dto.*;
import com.megazone.ERPSystem_phase3_Common.financial.service.ledger.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 총계정원장
@RestController
@RequiredArgsConstructor
public class GeneralApiController {
    private final GeneralService generalService;

    @PostMapping("/api/financial/ledger/general/show")
    public ResponseEntity<Object> generalShow(@RequestBody GeneralDTO dto) {

        List<GeneralAccountListDTO> generalShowDTOS = generalService.getGeneralShow(dto);

        return generalShowDTOS.isEmpty() ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회가능한 전표가 없습니다.") :
                ResponseEntity.status(HttpStatus.OK).body(generalShowDTOS);
    }

    @PostMapping("/api/financial/ledger/general/selectShow")
    public ResponseEntity<GeneralShowAllDTO> selectGeneralShow(@RequestBody GeneralSelectDTO dto) {

        GeneralShowAllDTO generalShowAllDTO = generalService.getGeneralSelectShow(dto);

        return generalShowAllDTO != null ? ResponseEntity.status(HttpStatus.OK).body(generalShowAllDTO) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
