package com.megazone.ERPSystem_phase3_Monolithic.financial.model.basic_information_management.client.dto;

import lombok.*;

/**
 * 업체 담당자 정보 DTO
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ManagerInfoDTO {
    private String clientManagerPhoneNumber; // 업체 담당자 전화번호
    private String clientManagerEmail; // 업체 담당자 이메일
}