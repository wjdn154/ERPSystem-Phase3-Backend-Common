package com.megazone.ERPSystem_phase3_FinanceHR.financial.model.voucher_entry.general_voucher_entry.dto;

import com.megazone.ERPSystem_phase3_FinanceHR.financial.model.voucher_entry.general_voucher_entry.UnresolvedVoucher;
import com.megazone.ERPSystem_phase3_FinanceHR.financial.model.voucher_entry.general_voucher_entry.enums.VoucherKind;
import com.megazone.ERPSystem_phase3_FinanceHR.financial.model.voucher_entry.general_voucher_entry.enums.VoucherType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnresolvedVoucherEntryDTO implements Cloneable {
//
//    private Long companyId; // 유저 회사 ID

    private String accountSubjectCode; // 계정과목 참조

    private String clientCode; // 거래처 참조

    private Long voucherManagerId; // 전표 담당자

    private String transactionDescription; // 거래 설명

    private VoucherType voucherType; // 전표 구분 ex) 입금, 출금, 차변, 대변

    private BigDecimal debitAmount; // 차변 금액

    private BigDecimal creditAmount; // 대변 금액

    private LocalDate voucherDate; // 전표 날짜 (거래발생일)

    private VoucherKind voucherKind; // 전표 종류
    // 객체 데이터 깊은 복사 메소드
    @Override
    public UnresolvedVoucherEntryDTO clone() throws CloneNotSupportedException {
        return (UnresolvedVoucherEntryDTO)super.clone();
    }


    public static UnresolvedVoucherEntryDTO create(UnresolvedVoucher unresolvedVoucher) {
        return new UnresolvedVoucherEntryDTO(
                unresolvedVoucher.getAccountSubject().getCode(),
                unresolvedVoucher.getClient().getCode(),
                unresolvedVoucher.getVoucherManager().getId(),
                unresolvedVoucher.getTransactionDescription(),
                unresolvedVoucher.getVoucherType(),
                unresolvedVoucher.getDebitAmount(),
                unresolvedVoucher.getCreditAmount(),
                unresolvedVoucher.getVoucherDate(),
                unresolvedVoucher.getVoucherKind());
    }


}
