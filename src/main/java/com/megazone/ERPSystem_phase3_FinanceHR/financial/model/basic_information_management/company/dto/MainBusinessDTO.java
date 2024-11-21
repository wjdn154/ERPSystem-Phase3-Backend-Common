package com.megazone.ERPSystem_phase3_FinanceHR.financial.model.basic_information_management.company.dto;

import com.megazone.ERPSystem_phase3_FinanceHR.financial.model.basic_information_management.company.MainBusiness;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainBusinessDTO {
    private String code; // 업종코드
    private String businessType; // 업태
    private String item; // 종목

    public MainBusinessDTO(MainBusinessDTO mainBusiness) {
        this.code = mainBusiness.getCode();
        this.businessType = mainBusiness.getBusinessType();
        this.item = mainBusiness.getItem();
    }

    public MainBusinessDTO(MainBusiness mainBusiness) {
        this.code = mainBusiness.getCode();
        this.businessType = mainBusiness.getBusinessType();
        this.item = mainBusiness.getItem();
    }
}
