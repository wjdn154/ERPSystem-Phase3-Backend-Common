package com.megazone.ERPSystem_phase3_Common.common.user.model.basic_information_management.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 사원 엔티티

@Data
@Entity(name = "employee")
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"users"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="employee_number",unique = true)
    private String employeeNumber; // 사원 번호

    @OneToOne(mappedBy = "employee",fetch = FetchType.LAZY, cascade = CascadeType.ALL , orphanRemoval = true) // Users 랑 1대1 참조
    private Users users;

    @Column(nullable = false)
    private String firstName; // 이름

    @Column(nullable = false)
    private String lastName; // 성

    @Column(nullable = false)
    private String email; // 이메일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false) // 부서 참조
    private Department department;
}