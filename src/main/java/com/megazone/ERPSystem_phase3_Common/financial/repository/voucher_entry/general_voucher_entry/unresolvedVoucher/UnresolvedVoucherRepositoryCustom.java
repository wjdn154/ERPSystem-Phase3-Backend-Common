package com.megazone.ERPSystem_phase3_Common.financial.repository.voucher_entry.general_voucher_entry.unresolvedVoucher;

import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.general_voucher_entry.dto.UnresolvedVoucherApprovalDTO;
import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.general_voucher_entry.dto.UnresolvedVoucherDeleteDTO;
import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.general_voucher_entry.UnresolvedVoucher;

import java.time.LocalDate;
import java.util.List;

public interface UnresolvedVoucherRepositoryCustom {
    List<Long> deleteVoucherByManager(UnresolvedVoucherDeleteDTO dto);
    List<UnresolvedVoucher> findApprovalTypeVoucher(UnresolvedVoucherApprovalDTO dto);
    List<UnresolvedVoucher> ApprovalAllSearch(LocalDate date);
}
