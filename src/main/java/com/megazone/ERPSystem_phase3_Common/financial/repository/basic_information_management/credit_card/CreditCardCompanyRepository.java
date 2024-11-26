package com.megazone.ERPSystem_phase3_Common.financial.repository.basic_information_management.credit_card;

import com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.credit_card.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardCompanyRepository extends JpaRepository<Company, Long> {
}