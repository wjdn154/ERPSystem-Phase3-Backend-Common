package com.megazone.ERPSystem_phase3_FinanceHR.hr.repository.basic_information_management.Performance;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PerformanceRepositoryImpl implements PerformanceRepositoryCustom {
    private final JPAQueryFactory queryFactory;
}
