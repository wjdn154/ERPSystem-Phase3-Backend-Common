package com.megazone.ERPSystem_phase3_Common.financial.service.ledger;

import com.megazone.ERPSystem_phase3_Common.financial.model.ledger.dto.TaxInvoiceLedgerSearchDTO;
import com.megazone.ERPSystem_phase3_Common.financial.model.ledger.dto.TaxInvoiceLedgerShowAllDTO;

import java.util.List;

public interface TaxInvoiceLedgerService {
    List<TaxInvoiceLedgerShowAllDTO> show(TaxInvoiceLedgerSearchDTO dto);
}
