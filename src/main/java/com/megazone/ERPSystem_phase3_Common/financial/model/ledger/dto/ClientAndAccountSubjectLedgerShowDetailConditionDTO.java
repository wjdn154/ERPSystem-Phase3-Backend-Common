package com.megazone.ERPSystem_phase3_Common.financial.model.ledger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientAndAccountSubjectLedgerShowDetailConditionDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private String startAccountSubjectCode;
    private String endAccountSubjectCode;
    private Long clientId;
}
