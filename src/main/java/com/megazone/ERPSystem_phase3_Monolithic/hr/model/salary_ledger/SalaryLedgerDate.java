package com.megazone.ERPSystem_phase3_Monolithic.hr.model.salary_ledger;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "salary_ledger_date")
@Table(name = "salary_ledger_date")
public class SalaryLedgerDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Unique
    private Long id;

    private String code;

    private String description;

    private LocalDate startDate; // 마감시작기준

    private LocalDate endDate; // 마감일

}
