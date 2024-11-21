package com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_configuration.dto;

import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_configuration.Allowance;
import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_configuration.enums.TaxType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllowanceShowDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private TaxType taxType;

    public static AllowanceShowDTO create(Allowance allowance) {
        return new AllowanceShowDTO(
                allowance.getId(),
                allowance.getCode(),
                allowance.getName(),
                allowance.getDescription(),
                allowance.getTaxType()
        );
    }
}
