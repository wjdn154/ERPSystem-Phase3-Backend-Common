package com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.sales_and_purchase_voucher_entry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JournalEntryShowDTO {
    private String journalEntryCode;
    private String journalEntryName;

    public static JournalEntryShowDTO create(String journalEntryCode, String journalEntryName) {
        return new JournalEntryShowDTO(
                journalEntryCode,
                journalEntryName
        );
    }
}
