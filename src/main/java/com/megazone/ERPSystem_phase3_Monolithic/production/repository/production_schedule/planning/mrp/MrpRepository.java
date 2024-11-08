package com.megazone.ERPSystem_phase3_Monolithic.production.repository.production_schedule.planning.mrp;

import com.megazone.ERPSystem_phase3_Monolithic.production.model.production_schedule.planning.Mps;
import com.megazone.ERPSystem_phase3_Monolithic.production.model.production_schedule.planning.mrp.Mrp;
import com.megazone.ERPSystem_phase3_Monolithic.production.repository.production_schedule.planning.mps.MpsRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MrpRepository extends JpaRepository<Mrp, Long>, MrpRepositoryCustom {
}
