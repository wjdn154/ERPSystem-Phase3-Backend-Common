package com.megazone.ERPSystem_phase3_FinanceHR.financial.service.ledger;

import com.megazone.ERPSystem_phase3_FinanceHR.financial.model.voucher_entry.general_voucher_entry.UnresolvedVoucher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface JournalService {

    List<UnresolvedVoucher> journalSearch(LocalDate StartDate, LocalDate EndDate);

    List<BigDecimal> journalTotalAmount(LocalDate StartDate, LocalDate EndDate);

    BigDecimal journalTotalCount(LocalDate StartDate, LocalDate EndDate);
}
