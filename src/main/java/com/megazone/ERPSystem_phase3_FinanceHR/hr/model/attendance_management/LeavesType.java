package com.megazone.ERPSystem_phase3_FinanceHR.hr.model.attendance_management;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// 휴가 유형 정보를 저장

@Data
@Entity(name="leaves_leavestype")
@Table(name="leaves_leavestype")
@NoArgsConstructor
@AllArgsConstructor
public class LeavesType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "leavesType", fetch = FetchType.LAZY)
    private List<Leaves> leaves;

    @Column(nullable = false)
    private String typeName; // 휴가 유형명( 예 : 병가, 연차, 개인 휴가 등)
}
