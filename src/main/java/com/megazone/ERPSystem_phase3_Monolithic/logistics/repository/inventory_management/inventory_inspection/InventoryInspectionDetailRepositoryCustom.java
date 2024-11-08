package com.megazone.ERPSystem_phase3_Monolithic.logistics.repository.inventory_management.inventory_inspection;

import com.megazone.ERPSystem_phase3_Monolithic.logistics.model.inventory_management.inventory_adjustment.InventoryInspectionDetail;

import java.time.LocalDate;
import java.util.List;

public interface InventoryInspectionDetailRepositoryCustom {
    List<InventoryInspectionDetail> findByInspectionDateBetween(LocalDate startDate, LocalDate endDate);
}
