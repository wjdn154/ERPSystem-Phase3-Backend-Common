package com.megazone.ERPSystem_phase3_Common.company.repository.basic_information_management.company;

import com.megazone.ERPSystem_phase3_Common.company.model.basic_information_management.company.CorporateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorporateTypeRepository extends JpaRepository<CorporateType, Long> {
    Optional<CorporateType> findByCode(String code);
}