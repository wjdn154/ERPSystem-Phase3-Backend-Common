package com.megazone.ERPSystem_phase3_Common.financial.repository.basic_information_management.bank_account;

import com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.bank_account.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
}