package com.megazone.ERPSystem_phase3_Monolithic.financial.model.ledger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountLedgerShowDTO {
    private Long voucherId;
    private LocalDate voucherDate;
    private String transactionDescription;
    private String clientCode;
    private String clientName;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    private BigDecimal cashAmount;
    private Long voucherNumber;
    private LocalDateTime voucherRegistrationTime;
    private String departmentName;
    private String voucherManagerCode;
    private String voucherManagerName;
//    private String creditCardCode;
//    private String creditCardName;


    public static AccountLedgerShowDTO create(Long voucherId, LocalDate voucherDate, String transactionDescription, String clientCode,
                                              String clientName, BigDecimal debitAmount, BigDecimal creditAmount, BigDecimal cashAmount,
                                              Long voucherNumber, LocalDateTime voucherRegistrationTime, String departmentName,
                                              String voucherManagerCode, String voucherManagerName) {
        return new AccountLedgerShowDTO(
                voucherId,
                voucherDate,
                transactionDescription,
                clientCode,
                clientName,
                debitAmount,
                creditAmount,
                cashAmount,
                voucherNumber,
                voucherRegistrationTime,
                departmentName,
                voucherManagerCode,
                voucherManagerName
        );

    }
}
