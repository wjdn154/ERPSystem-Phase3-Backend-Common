package com.megazone.ERPSystem_phase3_Common.financial.service.ledger;

import com.megazone.ERPSystem_phase3_Common.financial.model.ledger.dto.CashJournalSearchDTO;
import com.megazone.ERPSystem_phase3_Common.financial.model.ledger.dto.CashJournalShowAllListDTO;

public interface CashJournalService {
    CashJournalShowAllListDTO showAll(CashJournalSearchDTO dto);
}
