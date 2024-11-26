package com.megazone.ERPSystem_phase3_Common.financial.controller.ledger;

import com.megazone.ERPSystem_phase3_Common.financial.model.ledger.dto.TaxInvoiceLedgerSearchDTO;
import com.megazone.ERPSystem_phase3_Common.financial.model.ledger.dto.TaxInvoiceLedgerShowAllDTO;
import com.megazone.ERPSystem_phase3_Common.financial.service.ledger.TaxInvoiceLedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaxInvoiceLedgerApiController {
    private final TaxInvoiceLedgerService taxInvoiceLedgerService;


    @PostMapping("/api/financial/ledger/taxInvoice/show")
    public ResponseEntity<Object> show(@RequestBody TaxInvoiceLedgerSearchDTO dto ) {
        List<TaxInvoiceLedgerShowAllDTO> showResult = taxInvoiceLedgerService.show(dto);
        return !showResult.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(showResult) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).body("해당하는 내용이 없습니다.");
    }
}
