package com.megazone.ERPSystem_phase3_FinanceHR.financial.controller.voucher_entry.sales_and_purchase_voucher_entry;

import com.megazone.ERPSystem_phase3_FinanceHR.financial.model.voucher_entry.sales_and_purchase_voucher_entry.dto.JournalEntryShowDTO;
import com.megazone.ERPSystem_phase3_FinanceHR.financial.repository.voucher_entry.sales_and_purchase_voucher_entry.journalEntry.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JournalEntryApiController {
    private final JournalEntryRepository journalEntryRepository;

    @PostMapping("/api/financial/journalEntry/show")
    public ResponseEntity<List<JournalEntryShowDTO>> showAll() {
        List<JournalEntryShowDTO> journalEntryShowDTOS = journalEntryRepository.findDistinctCodeAndName()
                .stream().map((select) -> {
                    return JournalEntryShowDTO.create(
                            (String) select[0],
                            (String) select[1]
                    );
                }).toList();

        return !journalEntryShowDTOS.isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(journalEntryShowDTOS) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

//    @PostMapping("/api/financial/journalEntry/id")
//    public ResponseEntity<Object> journalEntryGet(@RequestParam("journalEntryCode") String journalEntryCode) {
//        try {
//            Long journalEntryId = journalEntryRepository.find
//        }
//        catch(Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
}
