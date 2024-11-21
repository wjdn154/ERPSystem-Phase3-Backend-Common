package com.megazone.ERPSystem_phase3_FinanceHR.financial.model.ledger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesAndPurChaseLedgerQuarterlySumDTO {
    private Integer quarter;
    private Integer voucherCount;
    private BigDecimal sumSupplyAmount;
    private BigDecimal sumVatAmount;
    private BigDecimal sumAmount;

    public static SalesAndPurChaseLedgerQuarterlySumDTO create(Integer quarter, Integer voucherCount, BigDecimal sumSupplyAmount,
                                                               BigDecimal sumVatAmount, BigDecimal sumAmount) {
        return new SalesAndPurChaseLedgerQuarterlySumDTO(quarter, voucherCount, sumSupplyAmount, sumVatAmount, sumAmount);
    }
}
