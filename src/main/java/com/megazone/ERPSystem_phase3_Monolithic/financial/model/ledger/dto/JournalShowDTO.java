package com.megazone.ERPSystem_phase3_Monolithic.financial.model.ledger.dto;

import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.dto.ResolvedVoucherShowAllDTO;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.dto.ResolvedVoucherShowDTO;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.dto.UnresolvedVoucherShowDTO;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.enums.VoucherKind;
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
