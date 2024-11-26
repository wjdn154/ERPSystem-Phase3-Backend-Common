package com.megazone.ERPSystem_phase3_Common.hr.model.basic_configuration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllowanceUpdateDTO {
    private Long id;
    private Long name;
    private String description;
}
