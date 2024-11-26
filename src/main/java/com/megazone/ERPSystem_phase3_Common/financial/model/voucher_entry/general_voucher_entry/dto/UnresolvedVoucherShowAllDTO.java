package com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.general_voucher_entry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UnresolvedVoucherShowAllDTO {
    private LocalDate searchDate;
    private List<UnresolvedVoucherShowDTO> voucherDtoList;
    private BigDecimal cashAmount;
    private BigDecimal totalDebit;
    private BigDecimal totalCredit;
    private BigDecimal diffCashAmount;

    public static UnresolvedVoucherShowAllDTO create(LocalDate searchDate, List<UnresolvedVoucherShowDTO> voucherDtoList, BigDecimal cashAmount,
                                                     BigDecimal totalDebit, BigDecimal totalCredit) {
        return new UnresolvedVoucherShowAllDTO(
                searchDate,
                voucherDtoList,
                cashAmount,
                totalDebit,
                totalCredit,
                totalDebit.subtract(totalCredit)
        );
    }



}
