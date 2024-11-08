package com.megazone.ERPSystem_phase3_Monolithic.financial.repository.basic_information_management.company;

import com.megazone.ERPSystem_phase3_Monolithic.financial.model.basic_information_management.company.Company;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.basic_information_management.company.MainBusiness;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByBusinessRegistrationNumber(String businessRegistrationNumber);
    Optional<Company> findByCorporateRegistrationNumber(String corporateRegistrationNumber);
    List<Company> findByNameContaining(String searchText);
}