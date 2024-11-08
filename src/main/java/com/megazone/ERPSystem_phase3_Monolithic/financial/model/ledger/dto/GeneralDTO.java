package com.megazone.ERPSystem_phase3_Monolithic.financial.model.ledger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private String startSubjectCode;
    private String endSubjectCode;
}
