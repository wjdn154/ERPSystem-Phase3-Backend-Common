package com.megazone.ERPSystem_phase3_FinanceHR.hr.model.salary_ledger.dto;

import com.megazone.ERPSystem_phase3_FinanceHR.hr.model.salary_ledger.PaymentStatusType;
import lombok.Data;

@Data
public class PaymentStatusManagementSearchDTO {
    private Long salaryStartId;
    private Long salaryEndId;
    private PaymentStatusType paymentStatusType;
}
