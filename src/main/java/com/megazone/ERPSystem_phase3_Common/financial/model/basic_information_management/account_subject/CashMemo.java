package com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.account_subject;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 현금적요 테이블
 */
@Entity(name = "account_subject_cash_memo")
@Table(name = "account_subject_cash_memo")
@Getter
@Setter
public class CashMemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 적요 ID

    private String code; // 적요 코드

    @Column(nullable = false)
    private String content; // 적요 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_subject_code", referencedColumnName = "code")
    private AccountSubject accountSubject; // 참조하는 계정과목

    public CashMemo() {
    }

    public CashMemo(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}