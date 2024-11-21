package com.megazone.ERPSystem_phase3_FinanceHR.financial.model.voucher_entry.sales_and_purchase_voucher_entry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnresolvedSaleAndPurchaseVoucherShowAllDTO {
//    private LocalDate date;
    private List<UnresolvedSaleAndPurchaseVoucherShowDTO> showDTOS;

    public static UnresolvedSaleAndPurchaseVoucherShowAllDTO create(/*LocalDate localDate,*/List<UnresolvedSaleAndPurchaseVoucherShowDTO> showDTOS) {
        return new UnresolvedSaleAndPurchaseVoucherShowAllDTO(/*localDate,*/ showDTOS);
    }

}
