package com.megazone.ERPSystem_phase3_Monolithic.financial.service.basic_information_management.account_subject;

import com.megazone.ERPSystem_phase3_Monolithic.financial.model.basic_information_management.account_subject.dto.AccountSubjectDTO;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.basic_information_management.account_subject.dto.AccountSubjectsAndMemosDTO;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.basic_information_management.account_subject.dto.CashMemoDTO;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.basic_information_management.account_subject.dto.MemoRequestDTO;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface AccountSubjectService {
    Optional<AccountSubjectsAndMemosDTO> findAllAccountSubjectDetails();

    Optional<Object> addMemoToAccountSubject (String accountSubjectCode, MemoRequestDTO memoRequestDTO);

    Optional<AccountSubjectDTO> updateAccountSubject(String code, AccountSubjectDTO dto);

    Optional<AccountSubjectDTO> saveAccountSubject(AccountSubjectDTO dto);

    List<AccountSubjectDTO> searchAccountSubject(String searchText);

    void deleteAccount(String code);

}