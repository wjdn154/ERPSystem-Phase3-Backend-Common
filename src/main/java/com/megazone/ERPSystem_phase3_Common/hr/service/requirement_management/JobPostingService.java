package com.megazone.ERPSystem_phase3_Common.hr.service.requirement_management;

import com.megazone.ERPSystem_phase3_Common.hr.model.requirement_management.jobposting.DTO.JobPostingDTO;

import java.util.List;

public interface JobPostingService {
    List<JobPostingDTO> findAllJobPostings();
}
