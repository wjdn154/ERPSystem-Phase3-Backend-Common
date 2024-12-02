package com.megazone.ERPSystem_phase3_Common.company.repository.common;

import com.megazone.ERPSystem_phase3_Common.company.model.common.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}