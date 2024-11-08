package com.megazone.ERPSystem_phase3_Monolithic.production.repository.resource_data.materialData;

import com.megazone.ERPSystem_phase3_Monolithic.production.model.resource_data.MaterialData;
import com.megazone.ERPSystem_phase3_Monolithic.production.model.resource_data.dto.ListMaterialDataDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MaterialDataRepository extends JpaRepository<MaterialData, Long> , MaterialDataRepositoryCustom{


    boolean existsByMaterialCode(String materialCode);

    boolean existsByMaterialCodeAndIdNot(String materialCode, Long id);

    List<MaterialData> findAllByOrderByIdDesc();

}
