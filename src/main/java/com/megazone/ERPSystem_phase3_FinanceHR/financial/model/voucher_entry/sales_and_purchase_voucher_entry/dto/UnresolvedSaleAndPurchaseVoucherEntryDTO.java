package com.megazone.ERPSystem_phase3_FinanceHR.financial.model.voucher_entry.sales_and_purchase_voucher_entry.dto;

import com.megazone.ERPSystem_phase3_FinanceHR.financial.model.voucher_entry.sales_and_purchase_voucher_entry.enums.ElectronicTaxInvoiceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnresolvedSaleAndPurchaseVoucherEntryDTO {
    private String vatTypeCode;
    private String clientCode;
    private String journalEntryCode;
    private Long voucherManagerId;
    private LocalDate voucherDate;
    private String itemName;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal supplyAmount;
    private ElectronicTaxInvoiceStatus electronicTaxInvoiceStatus;
}
