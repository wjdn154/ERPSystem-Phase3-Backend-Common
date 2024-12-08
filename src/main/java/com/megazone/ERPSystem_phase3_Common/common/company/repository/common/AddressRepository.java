package com.megazone.ERPSystem_phase3_Common.common.company.repository.common;

import com.megazone.ERPSystem_phase3_Common.common.company.model.common.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}