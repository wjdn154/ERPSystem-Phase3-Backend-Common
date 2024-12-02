package com.megazone.ERPSystem_phase3_Common.Integrated.repository.dashboard;

import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.EnvironmentalCertificationAssessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.Optional;

public interface EnvironmentalCertificationAssessmentRepository extends JpaRepository<EnvironmentalCertificationAssessment, Long> {
    Optional<EnvironmentalCertificationAssessment> findByMonth(YearMonth month);
}