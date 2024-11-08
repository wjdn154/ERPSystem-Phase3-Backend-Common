package com.megazone.ERPSystem_phase3_Monolithic.financial.controller.voucher_entry.general_voucher_entry;

import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.ResolvedVoucher;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.dto.ResolvedVoucherDeleteDTO;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.dto.ResolvedVoucherShowAllDTO;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.dto.ResolvedVoucherShowDTO;
import com.megazone.ERPSystem_phase3_Monolithic.financial.service.voucher_entry.general_voucher_entry.ResolvedVoucherService;
import com.megazone.ERPSystem_phase3_Monolithic.hr.model.basic_information_management.employee.Users;
import com.megazone.ERPSystem_phase3_Monolithic.hr.repository.basic_information_management.Users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ResolvedVoucherApiController {
    private final ResolvedVoucherService resolvedVoucherService;
    private final UsersRepository usersRepository;

    /**
     * 승인된 일반전표 조회
     * @param requestData
     * @return
     */
    @PostMapping("/api/financial/general-voucher-entry/showResolvedVoucherEntry")
    public ResponseEntity<Object> showAllResolvedVoucher(@RequestBody Map<String, LocalDate> requestData) {
        LocalDate date = requestData.get("searchDate");

        List<ResolvedVoucher> resolvedVoucherList = resolvedVoucherService.resolvedVoucherAllSearch(date);

        List<ResolvedVoucherShowDTO> showDtos = resolvedVoucherList.stream().map(
                (voucher) -> { return ResolvedVoucherShowDTO.create(voucher); }).toList();

        ResolvedVoucherShowAllDTO showAllDto = ResolvedVoucherShowAllDTO.create(
                date,
                showDtos,
                BigDecimal.ZERO,  // 현재잔액 기능 구현필요 <<<<
                resolvedVoucherService.totalDebit(date),
                resolvedVoucherService.totalCredit(date)
        );

        return (!resolvedVoucherList.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK).body(showAllDto) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).body("해당하는 전표가 없습니다.");
    }

    /**
     * 일반전표 삭제 메소드
     * @param dto
     * @return
     */
    @PostMapping("api/financial/general-voucher-entry/deleteResolvedVoucher")
    public ResponseEntity<String> deleteResolvedVoucher(@RequestBody ResolvedVoucherDeleteDTO dto) {

        List<Long> resolvedVoucherList = resolvedVoucherService.deleteResolvedVoucher(dto);

        return (!resolvedVoucherList.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK).body("삭제가 완료되었습니다.") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제가능한 전표가 없습니다.");
    }


    // 날짜와 계정과목으로 조회
}
