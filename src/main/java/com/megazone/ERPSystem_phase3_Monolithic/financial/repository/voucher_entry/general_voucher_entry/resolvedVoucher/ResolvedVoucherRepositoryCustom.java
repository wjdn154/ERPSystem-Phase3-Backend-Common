package com.megazone.ERPSystem_phase3_Monolithic.financial.repository.voucher_entry.general_voucher_entry.resolvedVoucher;

import com.megazone.ERPSystem_phase3_Monolithic.financial.model.financial_statements.IncomeStatementLedgerPrintType;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.financial_statements.dto.*;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.ledger.dto.*;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.dto.ResolvedVoucherDeleteDTO;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.dto.ResolvedVoucherShowDTO;

import java.util.List;

public interface ResolvedVoucherRepositoryCustom {
    List<Long> deleteVoucherByManager(ResolvedVoucherDeleteDTO dto);

    List<GeneralShowDTO> generalSelectShow(GeneralSelectDTO dto);

    List<GeneralAccountListDTO> generalList(GeneralDTO dto);

    List<ClientLedgerShowDTO> clientLedgerList(ClientLedgerSearchDTO dto);

    List<ClientListDTO> clientAndAccountSubjectLedgerList(ClientAndAccountSubjectLedgerSearchDTO dto);

    List<ClientAndAccountSubjectLedgerShowDetailDTO> clientAndAccountSubjectLedgerDetail(ClientAndAccountSubjectLedgerShowDetailConditionDTO dto);

    List<CashJournalShowDTO> cashJournalShow(CashJournalSearchDTO dto);

    List<AccountLedgerAccListDTO> accountLedgerList(AccountLedgerSearchDTO dto);

    List<AccountLedgerShowDTO> accountLedgerDetail(AccountLedgerDetailEntryDTO dto);

    List<DailyAndMonthJournalDTO> dailyLedgerList(DailyAndMonthJournalSearchDTO dto);

    List<ResolvedVoucherShowDTO> voucherPrintShowList(VoucherPrintSearchDTO dto);

    List<FinancialStatementsLedgerDTO> financialStatementsShow(FinancialStatementsLedgerSearchDTO dto);

    List<IncomeStatementLedgerDTO> incomeStatementShow(IncomeStatementSearchDTO dto);
}
