package com.megazone.ERPSystem_phase3_FinanceHR.financial.controller.ledger;

import com.megazone.ERPSystem_phase3_FinanceHR.financial.model.ledger.dto.SalesAndPurChaseLedgerSearchDTO;
import com.megazone.ERPSystem_phase3_FinanceHR.financial.model.ledger.dto.SalesAndPurChaseLedgerShowAllDTO;
import com.megazone.ERPSystem_phase3_FinanceHR.financial.service.ledger.SalesAndPurchaseLedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SalesAndPurchaseLedgerController {
    private final SalesAndPurchaseLedgerService salesAndPurchaseLedgerService;


    @PostMapping("/api/financial/ledger/SalesAndPurchase/show")
    public ResponseEntity<SalesAndPurChaseLedgerShowAllDTO> show(@RequestBody SalesAndPurChaseLedgerSearchDTO dto) {
        SalesAndPurChaseLedgerShowAllDTO resultDTO = salesAndPurchaseLedgerService.showAll(dto);

        return ResponseEntity.status(HttpStatus.OK).body(resultDTO);
    }
}