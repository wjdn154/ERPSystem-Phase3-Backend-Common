package com.megazone.ERPSystem_phase3_Monolithic.financial.model.ledger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JournalDTO {
    LocalDate startDate;
    LocalDate endDate;
}
