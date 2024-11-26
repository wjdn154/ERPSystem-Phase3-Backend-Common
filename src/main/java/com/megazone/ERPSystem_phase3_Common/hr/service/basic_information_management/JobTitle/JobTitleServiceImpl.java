package com.megazone.ERPSystem_phase3_Common.hr.service.basic_information_management.JobTitle;

import com.megazone.ERPSystem_phase3_Common.hr.model.basic_information_management.employee.JobTitle;
import com.megazone.ERPSystem_phase3_Common.hr.repository.basic_information_management.JobTitle.JobTitleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class JobTitleServiceImpl implements JobTitleService {
    private final JobTitleRepository jobTitleRepository;

    @Override
    public Optional<JobTitle> getJobTitleById(Long id) {
        return jobTitleRepository.findById(id);
    }
}
