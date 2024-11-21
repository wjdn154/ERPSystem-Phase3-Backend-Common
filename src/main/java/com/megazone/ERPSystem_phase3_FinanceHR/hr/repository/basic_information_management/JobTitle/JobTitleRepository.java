package com.megazone.ERPSystem_phase3_FinanceHR.hr.repository.basic_information_management.JobTitle;

import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_information_management.employee.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobTitleRepository extends JpaRepository<JobTitle, Long>, JobTitleRepositoryCustom {

    Optional<JobTitle> findByJobTitleName(String name);
}
