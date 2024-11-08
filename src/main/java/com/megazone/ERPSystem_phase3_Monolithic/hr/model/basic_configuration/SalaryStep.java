package com.megazone.ERPSystem_phase3_Monolithic.hr.model.basic_configuration;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employee_salarystep")
@Table(name = "employee_salarystep")
/**
 *  호봉 정보 테이블
 */
public class SalaryStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code; // 호봉코드

    @Column(nullable = false)
    private String name; // 호봉 1호봉,2호봉 ~~ 10호봉

    private String description;
}
