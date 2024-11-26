package com.megazone.ERPSystem_phase3_Common.financial.model.ledger.dto;

import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.sales_and_purchase_voucher_entry.enums.ElectronicTaxInvoiceStatus;
import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.sales_and_purchase_voucher_entry.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesAndPurChaseLedgerShowDTO {
    private Long voucherId;
    private String vatTypeName;
    private TransactionType transactionType;
    private String accountSubjectCode;
    private String accountSubjectName;
    private LocalDate voucherDate;
    private Long voucherNumber;
    private String itemName;
    private BigDecimal supplyAmount;
    private BigDecimal vatAmount;
    private BigDecimal sumAmount;
    private String clientCode;
    private String clientName;
    private ElectronicTaxInvoiceStatus electronicTaxInvoiceStatus;
    private String journalEntryName;
//    private String creditCardCode;
//    private String bankAccountName;
//    private String bankAccountCode;
    private String voucherManagerCode;
    private String voucherManagerDepartmentName;
    private String voucherManagerName;

    public static SalesAndPurChaseLedgerShowDTO create(Long voucherId, String vatTypeName, TransactionType transactionType,
                                         String accountSubjectCode, String accountSubjectName, LocalDate voucherDate, Long voucherNumber, String itemName,
                                         BigDecimal supplyAmount, BigDecimal vatAmount, BigDecimal sumAmount, String clientCode,
                                         String clientName, ElectronicTaxInvoiceStatus electronicTaxInvoiceStatus,
                                         String journalEntryName, /*String creditCardCode, String bankAccountName,
                                         String bankAccountCode,*/ String voucherManagerCode, String voucherManagerDepartmentName,
                                         String voucherManagerName) {
        return new SalesAndPurChaseLedgerShowDTO(
                voucherId,
                vatTypeName,
                transactionType,
                accountSubjectCode,
                accountSubjectName,
                voucherDate,
                voucherNumber,
                itemName,
                supplyAmount,
                vatAmount,
                sumAmount,
                clientCode,
                clientName,
                electronicTaxInvoiceStatus,
                journalEntryName,
//                creditCardCode,
//                bankAccountName,
//                bankAccountCode,
                voucherManagerCode,
                voucherManagerDepartmentName,
                voucherManagerName
        );
    }
}
