package com.megazone.ERPSystem_phase3_Monolithic.financial.model.ledger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountLedgerDetailEntryDTO {
    private Long accountId;
    private LocalDate startDate;
    private LocalDate endDate;
}
