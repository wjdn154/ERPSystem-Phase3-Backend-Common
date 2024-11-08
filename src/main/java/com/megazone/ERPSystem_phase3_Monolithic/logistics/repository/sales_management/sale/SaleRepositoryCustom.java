package com.megazone.ERPSystem_phase3_Monolithic.logistics.repository.sales_management.sale;

import com.megazone.ERPSystem_phase3_Monolithic.logistics.model.purchase_management.dto.SearchDTO;
import com.megazone.ERPSystem_phase3_Monolithic.logistics.model.sales_management.Quotation;
import com.megazone.ERPSystem_phase3_Monolithic.logistics.model.sales_management.Sale;

import java.util.List;

public interface SaleRepositoryCustom {
    List<Sale> findBySearch(SearchDTO dto);
}
