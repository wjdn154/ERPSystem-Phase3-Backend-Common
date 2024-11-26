package com.megazone.ERPSystem_phase3_Common.financial.service.basic_information_management.credit_card;

import com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.credit_card.dto.CreditCardDTO;

import java.util.Optional;

public interface CreditCardService {
    Optional<CreditCardDTO> saveCreditCard(CreditCardDTO creditCardDTO);
    Optional<CreditCardDTO> updateCreditCard(Long id, CreditCardDTO creditCardDTO);
}