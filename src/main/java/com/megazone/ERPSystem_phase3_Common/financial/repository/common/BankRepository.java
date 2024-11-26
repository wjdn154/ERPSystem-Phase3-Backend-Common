package com.megazone.ERPSystem_phase3_Common.financial.repository.common;

import com.megazone.ERPSystem_phase3_Common.financial.model.common.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}