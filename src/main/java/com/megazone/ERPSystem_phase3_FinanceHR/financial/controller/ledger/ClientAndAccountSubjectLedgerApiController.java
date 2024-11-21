package com.megazone.ERPSystem_phase3_FinanceHR.financial.controller.ledger;

import com.megazone.ERPSystem_phase3_FinanceHR.financial.model.ledger.dto.*;
import com.megazone.ERPSystem_phase3_FinanceHR.financial.service.ledger.ClientLedgerAndAccountSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// 거래처별 계정과목별 원장
@RestController
@RequiredArgsConstructor
public class ClientAndAccountSubjectLedgerApiController {
    private final ClientLedgerAndAccountSubjectService clientLedgerAndAccountSubjectService;

    @PostMapping("/api/financial/ledger/clientAndAccountSubject/show")
    public ResponseEntity<Object> show(@RequestBody ClientAndAccountSubjectLedgerSearchDTO dto) {
        List<ClientListDTO> showClientList = clientLedgerAndAccountSubjectService.show(dto);

        return !showClientList.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(showClientList) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).body("검색 조건에 해당하는 사항이 없습니다.");
    }


    @PostMapping("/api/financial/ledger/clientAndAccountSubject/showDetail")
    public ResponseEntity<Object> showUnresolvedVoucher(@RequestBody ClientAndAccountSubjectLedgerShowDetailConditionDTO dto) {
        ClientAndAccountSubjectLedgerShowDetailAllDTO dtos = clientLedgerAndAccountSubjectService.showDetail(dto);

        return dtos != null ? ResponseEntity.status(HttpStatus.OK).body(dtos) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).body("조건에 해당하는 거래가 없습니다.");
    }
}
