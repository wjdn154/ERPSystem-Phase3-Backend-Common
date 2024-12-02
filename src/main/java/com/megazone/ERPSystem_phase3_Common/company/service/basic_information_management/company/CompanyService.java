package com.megazone.ERPSystem_phase3_Common.company.service.basic_information_management.company;

import com.megazone.ERPSystem_phase3_Common.company.model.basic_information_management.company.MainBusiness;
import com.megazone.ERPSystem_phase3_Common.company.model.basic_information_management.company.TaxOffice;
import com.megazone.ERPSystem_phase3_Common.company.model.basic_information_management.company.dto.CompanyDTO;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Optional<CompanyDTO> saveCompany(CompanyDTO companyDTO);
    Optional<CompanyDTO> updateCompany(Long id, CompanyDTO companyDTO);
    List<CompanyDTO> findAllCompany();
    List<CompanyDTO> searchCompany(String searchText);

    MainBusiness findMainBusiness(String code);

    TaxOffice findTaxOffice(String code);
}