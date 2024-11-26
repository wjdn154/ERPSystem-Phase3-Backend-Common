package com.megazone.ERPSystem_phase3_Common.financial.controller.voucher_entry.sales_and_purchase_voucher_entry;


import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.general_voucher_entry.UnresolvedVoucher;
import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.general_voucher_entry.dto.UnresolvedVoucherShowAllDTO;
import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.general_voucher_entry.dto.UnresolvedVoucherShowDTO;
import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.sales_and_purchase_voucher_entry.UnresolvedSaleAndPurchaseVoucher;
import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.sales_and_purchase_voucher_entry.dto.*;
import com.megazone.ERPSystem_phase3_Common.financial.service.voucher_entry.sales_and_purchase_voucher_entry.UnresolvedSaleAndPurchaseVoucherService;
import com.megazone.ERPSystem_phase3_Common.hr.repository.basic_information_management.Users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UnresolvedSaleAndPurchaseVoucherApiController {

    private final UnresolvedSaleAndPurchaseVoucherService unresolvedSaleAndPurchaseVoucherService;
    private final UsersRepository usersRepository;

    /**
     * 미결 매출매입전표 등록 기능
     * @param dto
     * @return
     */
    @PostMapping("/api/financial/sale-and-purchase-unresolved-voucher/entry")
    public ResponseEntity<String> Entry(@RequestBody UnresolvedSaleAndPurchaseVoucherEntryDTO dto) {

        UnresolvedSaleAndPurchaseVoucher unresolvedSaleAndPurchaseVoucher =
                unresolvedSaleAndPurchaseVoucherService.save(dto);
        return unresolvedSaleAndPurchaseVoucher != null ?
                ResponseEntity.status(HttpStatus.OK).body("등록이 완료되었습니다.") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 미결 매출매입전표 전체조회 기능
     * @param requestData
     * @return
     */
    @PostMapping("/api/financial/sale-end-purchase-unresolved-voucher/shows")
    public ResponseEntity<UnresolvedSaleAndPurchaseVoucherShowAllDTO> showAll(@RequestBody Map<String, LocalDate> requestData) {
        LocalDate date = requestData.get("searchDate");

        List<UnresolvedSaleAndPurchaseVoucher> voucherList = unresolvedSaleAndPurchaseVoucherService.searchAllVoucher(date);

//        if(voucherList.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
        List<UnresolvedSaleAndPurchaseVoucherShowDTO> showDTOS = voucherList.stream().map(
                UnresolvedSaleAndPurchaseVoucherShowDTO::create).toList();

        UnresolvedSaleAndPurchaseVoucherShowAllDTO showAllDTO = UnresolvedSaleAndPurchaseVoucherShowAllDTO.create(showDTOS);

        return ResponseEntity.status(HttpStatus.OK).body(showAllDTO);
    }


    /**
     * 매출매입전표 삭제
     * 삭제시 연결되어있는 미결전표도 모두 삭제
     * @param dto
     * @return
     */
    @PostMapping("/api/financial/sale-end-purchase-unresolved-voucher/deletes")
    public ResponseEntity<String> deleteVoucher(@RequestBody UnresolvedSaleAndPurchaseVoucherDeleteDTO dto) {


        String message = unresolvedSaleAndPurchaseVoucherService.deleteVoucher(dto);

        return (message != null) ?
                ResponseEntity.status(HttpStatus.OK).body("삭제가 완료되었습니다.") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제가능한 전표가 없습니다.");
    }

    /**
     * 선택한 매출매입전표의 분개 리스트를 출력하는 기능
     * @param voucherId
     * @return
     */
    @PostMapping("/api/financial/sale-end-purchase-unresolved-voucher/entryShow")
    public ResponseEntity<Object> showOne(@RequestBody Map<String, Long> voucherId) {
        try {
            Long searchId = voucherId.get("voucherId");
            List<UnresolvedVoucher> vouchers = unresolvedSaleAndPurchaseVoucherService.searchVoucher(searchId);

            if (vouchers == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("등록된 전표가 없습니다.");
            }

            List<UnresolvedVoucherShowDTO> showDTOs = vouchers.stream().map(
                    UnresolvedVoucherShowDTO::create).toList();

            UnresolvedVoucherShowAllDTO showAllDTO = UnresolvedVoucherShowAllDTO.create(
                    showDTOs.get(0).getVoucherDate(),
                    showDTOs,
                    BigDecimal.ZERO,
                    unresolvedSaleAndPurchaseVoucherService.totalDebit(vouchers),
                    unresolvedSaleAndPurchaseVoucherService.totalCredit(vouchers)
            );

            return ResponseEntity.status(HttpStatus.OK).body(showAllDTO);

        } catch (Exception e) {
            e.printStackTrace();  // 예외의 자세한 정보 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록된 전표가 없습니다.");
        }
    }

    /**
     * 미결 매출 매입전표 승인 처리 메소드
     */
    @PostMapping("/api/financial/sale-end-purchase-unresolved-voucher/approve")
    public ResponseEntity<Object> voucherApprovalProcessing(@RequestBody UnresolvedSaleAndPurchaseVoucherApprovalDTO dto) {

        try{
            List<UnresolvedSaleAndPurchaseVoucher> unresolvedVoucherList = unresolvedSaleAndPurchaseVoucherService.ApprovalProcessing(dto);
            return ResponseEntity.status(HttpStatus.OK).body("선택한 미결 매출매입전표가 " + dto.approvalResult() + "되었습니다.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이유 : " + e.getMessage() + " " + dto.approvalResult() + "처리 실패");
        }
    }

    /**
     * 미결전표 승인탭 전체조회
     */
    @PostMapping("/api/financial/sale-end-purchase-unresolved-voucher/approveSearch")
    public ResponseEntity<Object> voucherApprovalSearch(@RequestBody Map<String,LocalDate> requestData) {

        LocalDate date = requestData.get("searchDate");

        try {
            List<UnresolvedSaleAndPurchaseVoucherShowDTO> unresolvedVoucherList = unresolvedSaleAndPurchaseVoucherService.ApprovalSearch(date);
            return ResponseEntity.status(HttpStatus.OK).body(unresolvedVoucherList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
