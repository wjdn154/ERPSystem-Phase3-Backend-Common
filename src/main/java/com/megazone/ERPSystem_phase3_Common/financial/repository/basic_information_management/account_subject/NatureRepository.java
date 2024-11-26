package com.megazone.ERPSystem_phase3_Common.financial.repository.basic_information_management.account_subject;

import com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.account_subject.Nature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NatureRepository extends JpaRepository<Nature, Long> {
}