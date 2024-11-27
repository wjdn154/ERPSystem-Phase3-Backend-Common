package com.megazone.ERPSystem_phase3_Common.company.model.basic_information_management.company.dto;

import com.megazone.ERPSystem_phase3_Common.company.model.basic_information_management.company.TaxOffice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxOfficeDTO {
    private String code; // 세무서 코드
    private String region; // 세무서 지역

    public TaxOfficeDTO(TaxOfficeDTO businessTaxOffice) {
        this.code = businessTaxOffice.getCode();
        this.region = businessTaxOffice.getRegion();
    }

    public TaxOfficeDTO(TaxOffice businessTaxOffice) {
        this.code = businessTaxOffice.getCode();
        this.region = businessTaxOffice.getRegion();
    }
}
