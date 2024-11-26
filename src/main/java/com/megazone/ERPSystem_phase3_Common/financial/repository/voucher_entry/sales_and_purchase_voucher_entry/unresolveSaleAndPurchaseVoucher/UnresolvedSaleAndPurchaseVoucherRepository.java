package com.megazone.ERPSystem_phase3_Common.financial.repository.voucher_entry.sales_and_purchase_voucher_entry.unresolveSaleAndPurchaseVoucher;

import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.sales_and_purchase_voucher_entry.UnresolvedSaleAndPurchaseVoucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UnresolvedSaleAndPurchaseVoucherRepository extends JpaRepository<UnresolvedSaleAndPurchaseVoucher, Long>,UnresolvedSaleAndPurchaseVoucherRepositoryCustom {
    Optional<UnresolvedSaleAndPurchaseVoucher> findFirstByVoucherDateOrderByIdDesc(LocalDate date);

    List<UnresolvedSaleAndPurchaseVoucher> findByVoucherDateOrderByVoucherNumberAsc(LocalDate date);

    UnresolvedSaleAndPurchaseVoucher findByVoucherNumber(Long voucherNumber);

    // 담당자 ID 추가필요.
    void deleteByVoucherNumberAndVoucherDate(Long voucherNumber, LocalDate searchDate);

}
