package com.megazone.ERPSystem_phase3_Monolithic.hr.repository.basic_information_management.Transfer;

import com.megazone.ERPSystem_phase3_Monolithic.hr.model.basic_information_management.employee.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransferRepository extends JpaRepository<Transfer, Long>, TransferRepositoryCustom{
}
