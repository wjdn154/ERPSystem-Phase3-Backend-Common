package com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.sales_and_purchase_voucher_entry;

import com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.client.Client;
import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.general_voucher_entry.ResolvedVoucher;
import com.megazone.ERPSystem_phase3_Common.financial.model.voucher_entry.sales_and_purchase_voucher_entry.enums.ElectronicTaxInvoiceStatus;
import com.megazone.ERPSystem_phase3_Common.hr.model.basic_information_management.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "resolved_sale_and_purchase_voucher")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "resolved_sale_and_purchase_voucher")
public class ResolvedSaleAndPurchaseVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vatType_id",nullable = false)
    private VatType vatType; // 부가세 유형

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client; // 거래처 참조

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "journalEntry_id", nullable = false)
    private JournalEntry journalEntry; // 분개 유형 참조

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voucherManager_code", nullable = false)
    private Employee voucherManager; // 담당자 참조

    @OneToMany(cascade = CascadeType.ALL,  orphanRemoval = true)
    @JoinColumn(name = "resolved_vouchers_id")
    private List<ResolvedVoucher> resolvedVouchers;

    @Column(nullable = false)
    private Long voucherNumber;

    @Column(nullable = false)
    private LocalDate voucherDate; // 매출매입전표 거래날짜

    @Column(nullable = false)
    private String itemName; // 품목명

    @Column(nullable = false)
    private BigDecimal quantity; // 수량

    @Column(nullable = false)
    private BigDecimal unitPrice; // 단가

    @Column(nullable = false)
    private BigDecimal supplyAmount; // 공급가액

    @Column(nullable = false)
    private BigDecimal vatAmount; // 부가세

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ElectronicTaxInvoiceStatus electronicTaxInvoiceStatus; // 전자세금계산서 발행 상태

    @Column(nullable = false)
    private LocalDateTime voucherApproveTime; // 전표등록시간

}
