package com.megazone.ERPSystem_phase3_Monolithic.hr.repository.basic_information_management.JobTitle;

import com.megazone.ERPSystem_phase3_Monolithic.hr.model.basic_information_management.employee.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobTitleRepository extends JpaRepository<JobTitle, Long>, JobTitleRepositoryCustom {

    Optional<JobTitle> findByJobTitleName(String name);
}
