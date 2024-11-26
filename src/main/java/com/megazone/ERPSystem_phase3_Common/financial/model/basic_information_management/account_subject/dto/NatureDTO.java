package com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.account_subject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NatureDTO {
    private Long id; // ID
    private String code; // 성격 코드
    private String name; // 성격명
}