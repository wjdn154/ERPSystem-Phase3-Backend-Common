package com.megazone.ERPSystem_phase3_Common.company.repository.common;

import com.megazone.ERPSystem_phase3_Common.company.model.common.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}