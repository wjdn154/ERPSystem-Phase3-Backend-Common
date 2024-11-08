package com.megazone.ERPSystem_phase3_Monolithic.logistics.repository.sales_management.sale_plan;

import com.megazone.ERPSystem_phase3_Monolithic.logistics.model.purchase_management.dto.SearchDTO;
import com.megazone.ERPSystem_phase3_Monolithic.logistics.model.sales_management.SalePlan;

import java.util.List;

public interface SalePlanRepositoryCustom {
    List<SalePlan> findBySearch(SearchDTO dto);
}
