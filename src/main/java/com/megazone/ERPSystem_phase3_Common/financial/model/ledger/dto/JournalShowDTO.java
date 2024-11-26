package com.megazone.ERPSystem_phase3_Common.financial.model.ledger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JournalShowDTO {
    private List<JournalShowDetailDTO> journalShowDetailDTO;
    private BigDecimal totalDebit;
    private BigDecimal totalCredit;
    private BigDecimal totalVoucherCount;
    public static JournalShowDTO create(List<JournalShowDetailDTO> dtos, BigDecimal totalDebit,
                                        BigDecimal totalCredit, BigDecimal totalVoucherCount) {
        return new JournalShowDTO(
                dtos,
                totalDebit,
                totalCredit,
                totalVoucherCount
        );
    }
}
