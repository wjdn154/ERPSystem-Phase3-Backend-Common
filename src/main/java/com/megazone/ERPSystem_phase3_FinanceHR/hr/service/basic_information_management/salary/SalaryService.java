package com.megazone.ERPSystem_phase3_FinanceHR.hr.service.basic_information_management.salary;

import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_configuration.dto.SalaryEntryDTO;
import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.basic_configuration.dto.SalaryShowDto;

public interface SalaryService {
    void saveSalary(SalaryEntryDTO dto);

    SalaryShowDto show(Long employeeId);
}
