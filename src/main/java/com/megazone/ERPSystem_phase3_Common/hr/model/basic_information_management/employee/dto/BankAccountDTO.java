package com.megazone.ERPSystem_phase3_Common.hr.model.basic_information_management.employee.dto;


import com.megazone.ERPSystem_phase3_Common.hr.model.basic_information_management.employee.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDTO {
    private Long bankId;
    private String name;
    private String code;
    private String accountNumber;


    public static BankAccountDTO create(BankAccount bankAccount) {
        return new BankAccountDTO(
                bankAccount.getBank().getId(),
                bankAccount.getBank().getName(),
                bankAccount.getBank().getCode(),
                bankAccount.getAccountNumber());
    }

    public static BankAccountDTO create(Long id, String name, String code, String accountNumber) {
        return new BankAccountDTO(id, name, code, accountNumber);
    }
}
