package com.megazone.ERPSystem_phase3_FinanceHR.hr.repository.requirement_management.JobPosting;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobPostingRepositoryImpl implements JobPostingRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    private JobPostingRepository jobPostingRepository;
}
