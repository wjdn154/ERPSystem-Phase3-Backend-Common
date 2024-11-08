package com.megazone.ERPSystem_phase3_Monolithic.production.service.resource_data.HazardousMaterial;

import com.megazone.ERPSystem_phase3_Monolithic.production.model.resource_data.dto.HazardousMaterialDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface HazardousMaterialService {

    List<HazardousMaterialDTO> findAllHazardousMaterial();

    Optional<HazardousMaterialDTO> createHazardousMaterial(HazardousMaterialDTO dto);

    Optional<HazardousMaterialDTO> updateHazardousMaterial(Long id, HazardousMaterialDTO dto);

    void deleteHazardousMaterial(Long id);
}
