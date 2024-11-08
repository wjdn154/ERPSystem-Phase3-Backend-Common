package com.megazone.ERPSystem_phase3_Monolithic.hr.model.attendance_management;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.megazone.ERPSystem_phase3_Monolithic.hr.model.attendance_management.enums.AttendanceStatus;
import com.megazone.ERPSystem_phase3_Monolithic.hr.model.basic_information_management.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

//  직원의 출퇴근 기록을 저장

@Data
@Entity(name="attendance_management")
@Table(name="attendance_management")
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employee; // 사원 참조

    @Column(nullable = false,unique = true)
    private String attendanceCode; // 근태코드

    @Column(nullable = false)
    private LocalDate date; // 날짜

    @Column
    private LocalDateTime checkInTime; // 출근 시간

    @Column
    private LocalDateTime checkOutTime; // 퇴근 시간

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status; // 근무 상태

}
