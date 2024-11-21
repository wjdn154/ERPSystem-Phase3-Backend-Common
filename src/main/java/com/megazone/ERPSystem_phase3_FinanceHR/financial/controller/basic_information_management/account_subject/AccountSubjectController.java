package com.megazone.ERPSystem_phase3_FinanceHR.financial.controller.basic_information_management.account_subject;

import com.megazone.ERPSystem_phase3_FinanceHR.common.config.security.JwtUtil;
import com.megazone.ERPSystem_phase3_FinanceHR.financial.model.basic_information_management.account_subject.dto.*;
import com.megazone.ERPSystem_phase3_FinanceHR.financial.repository.basic_information_management.account_subject.AccountSubjectRepository;
import com.megazone.ERPSystem_phase3_FinanceHR.financial.repository.basic_information_management.company.CompanyRepository;
import com.megazone.ERPSystem_phase3_FinanceHR.financial.service.basic_information_management.account_subject.AccountSubjectService;
import com.megazone.ERPSystem_phase3_FinanceHR.hr.repository.basic_information_management.Users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/financial/accountSubjects")
@RequiredArgsConstructor
public class AccountSubjectController {


    private final AccountSubjectService accountSubjectService;
    private final AccountSubjectRepository accountSubjectRepository;
    private final CompanyRepository companyRepository;
    private final UsersRepository usersRepository;
    private final JwtUtil jwtUtil;

    /**
     * 모든 계정과목과 관련된 적요 정보를 가져옴.
     * @return 모든 계정과목과 적요 정보를 담은 AccountSubjectsAndMemosDTO 객체를 반환함.
     */
    @PostMapping("/")
    public ResponseEntity<AccountSubjectsAndMemosDTO> getAllAccountSubjectDetails() {

        // 서비스에서 모든 계정과목과 적요 정보 가져옴
        Optional<AccountSubjectsAndMemosDTO> response = accountSubjectService.findAllAccountSubjectDetails();
        // HTTP 200 상태로 응답 반환
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/search")
    public List<AccountSubjectSearchDTO> searchAccountSubject(@RequestBody Map<String, String> searchText) {
        return accountSubjectService.searchAccountSubject();
    }

    /**
     * 특정 코드에 해당하는 계정과목의 상세 정보를 조회함.
     * @param code 조회할 계정과목의 코드
     * @return 해당 코드의 계정과목 상세 정보를 담은 AccountSubjectDetailDTO 객체를 반환함.
     */
    @PostMapping("/{code}")
    public ResponseEntity<AccountSubjectDetailDTO> getAccountSubjectDetailByCode(@PathVariable("code") String code) {

        // 리포지토리에서 해당 코드의 계정과목 상세 정보 가져옴
        Optional<AccountSubjectDetailDTO> response = accountSubjectRepository.findAccountSubjectDetailByCode(code);

        // 해당 코드의 계정과목이 존재하지 않을 경우 404 상태 반환
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    /**
     * 계정과목에 적요를 추가함.
     * @param accountSubjectCode 계정과목 코드
     * @param memoRequestDTO 적요 정보를 담은 DTO
     * @return 성공 시 HTTP 200, 실패 시 에러 메시지를 포함한 HTTP 상태 반환.
     */
    @PostMapping("/saveMemo/{code}")
    public ResponseEntity<Object> addMemoToAccountSubject(@PathVariable("code") String accountSubjectCode, @RequestBody MemoRequestDTO memoRequestDTO) {

        Optional<Object> savedMemo = accountSubjectService.addMemoToAccountSubject(accountSubjectCode, memoRequestDTO);
        return savedMemo
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


    /**
     * 계정과목을 저장함.
     * @param accountSubjectDTO 저장할 계정과목 정보
     * @return 저장된 계정과목 DTO를 반환함.
     */
    @PostMapping("/save")
    public ResponseEntity<AccountSubjectDTO> saveAccountSubject(@RequestBody AccountSubjectDTO accountSubjectDTO) {

        Optional<AccountSubjectDTO> savedAccount = accountSubjectService.saveAccountSubject(accountSubjectDTO);
        return savedAccount
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    /**
     * 계정과목 정보를 업데이트함.
     * @param accountSubjectDTO 업데이트할 계정과목 정보
     * @return 업데이트된 계정과목을 반환함.
     */
    @PutMapping("/update/{code}")
    public ResponseEntity<AccountSubjectDTO> updateAccount(@PathVariable("code") String code, @RequestBody AccountSubjectDTO accountSubjectDTO) {

        Optional<AccountSubjectDTO> updatedAccount = accountSubjectService.updateAccountSubject(code, accountSubjectDTO);

        return updatedAccount
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    /**
     * 주어진 ID를 가진 계정과목을 삭제함.
     *
     * @param code 삭제할 계정과목의 ID
     * @return 성공적으로 삭제된 경우, 삭제된 계정과목을 반환함.
     */
    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> deleteAccount(@PathVariable("code") String code) {

        try {
            accountSubjectService.deleteAccount(code);
            return ResponseEntity.ok("계정과목을 삭제했습니다. 코드번호 : " + code);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()); // 계정과목을 찾지 못했을 때 404 반환
        }
    }
}