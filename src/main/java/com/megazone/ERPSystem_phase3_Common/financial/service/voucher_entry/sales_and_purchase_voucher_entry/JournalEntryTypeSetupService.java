package com.megazone.ERPSystem_phase3_Common.financial.service.voucher_entry.sales_and_purchase_voucher_entry;

import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.sales_and_purchase_voucher_entry.dto.JournalEntryTypeSetupShowDTO;
import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.sales_and_purchase_voucher_entry.dto.JournalEntryTypeSetupUpdateDTO;

import java.util.List;

public interface JournalEntryTypeSetupService {
    List<JournalEntryTypeSetupShowDTO> updateEntryTypeSetup(List<JournalEntryTypeSetupUpdateDTO> dto);
}
