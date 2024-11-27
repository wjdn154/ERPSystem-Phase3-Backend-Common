package com.megazone.ERPSystem_phase3_Common.company.repository.basic_information_management.company;

import com.megazone.ERPSystem_phase3_Common.company.model.basic_information_management.company.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyAddressRepository extends JpaRepository<Address, Long> {
}