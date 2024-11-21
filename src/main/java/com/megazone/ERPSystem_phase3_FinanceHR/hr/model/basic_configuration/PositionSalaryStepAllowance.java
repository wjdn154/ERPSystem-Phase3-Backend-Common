package com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_configuration;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import java.math.BigDecimal;

/**
 * 직급 호봉 기준의 수당목록
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "position_salary_step_allowance")
@Table(name = "position_salary_step_allowance")
public class PositionSalaryStepAllowance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Unique
    private Long id;

    @ManyToOne
    @JoinColumn(name = "position_salary_step_id", nullable = false)
    private PositionSalaryStep positionSalaryStep; // PositionSalaryStep 테이블과의 다대일 관계

    @ManyToOne
    @JoinColumn(name = "allowance_id", nullable = false)
    private Allowance allowance; // Allowance 테이블과의 다대일 관계

    private BigDecimal amount; // 수당금액
}
