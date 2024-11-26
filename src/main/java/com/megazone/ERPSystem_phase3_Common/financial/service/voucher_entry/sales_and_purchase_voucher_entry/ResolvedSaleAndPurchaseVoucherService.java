package com.megazone.ERPSystem_phase3_Common.financial.service.voucher_entry.sales_and_purchase_voucher_entry;

import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.general_voucher_entry.ResolvedVoucher;
import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.sales_and_purchase_voucher_entry.ResolvedSaleAndPurchaseVoucher;
import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.sales_and_purchase_voucher_entry.UnresolvedSaleAndPurchaseVoucher;
import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.sales_and_purchase_voucher_entry.dto.ResolvedSaleAndPurchaseVoucherDeleteDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public interface ResolvedSaleAndPurchaseVoucherService {
    List<ResolvedSaleAndPurchaseVoucher> searchAllVoucher(LocalDate date);

    void resolvedVoucherEntry(List<UnresolvedSaleAndPurchaseVoucher> unresolvedVoucherList);

    List<ResolvedVoucher> searchEntryVoucher(Long voucherId);

    BigDecimal calculateTotalAmount(List<ResolvedVoucher> vouchers, Function<ResolvedVoucher, BigDecimal> amount);

    BigDecimal totalDebit(List<ResolvedVoucher> vouchers);

    BigDecimal totalCredit(List<ResolvedVoucher> vouchers);

    String deleteVoucher(ResolvedSaleAndPurchaseVoucherDeleteDTO dto);
}
