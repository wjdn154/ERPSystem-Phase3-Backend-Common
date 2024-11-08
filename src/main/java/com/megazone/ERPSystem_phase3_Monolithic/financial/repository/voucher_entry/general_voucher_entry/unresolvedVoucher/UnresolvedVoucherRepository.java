package com.megazone.ERPSystem_phase3_Monolithic.financial.repository.voucher_entry.general_voucher_entry.unresolvedVoucher;

import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.ResolvedVoucher;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.UnresolvedVoucher;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.enums.ApprovalStatus;
import com.megazone.ERPSystem_phase3_Monolithic.financial.model.voucher_entry.general_voucher_entry.enums.VoucherKind;
import org.apache.catalina.Manager;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UnresolvedVoucherRepository extends JpaRepository<UnresolvedVoucher, Long>, UnresolvedVoucherRepositoryCustom {
    Optional<UnresolvedVoucher> findFirstByVoucherDateAndVoucherKindEqualsOrderByIdDesc(LocalDate voucherDate,VoucherKind voucherKind);
    List<UnresolvedVoucher> findByVoucherDateAndVoucherKindEqualsOrderByVoucherNumberAsc(LocalDate date,VoucherKind voucherKind);

    @Query("SELECT r FROM unresolved_voucher r WHERE r.voucherDate between :startDate AND :endDate")
    List<UnresolvedVoucher> journalSearch(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

    @Query("SELECT sum(r.debitAmount) FROM unresolved_voucher r WHERE r.voucherDate between :startDate AND :endDate")
    BigDecimal testTotalDebit(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

    @Query("SELECT sum(r.creditAmount) FROM unresolved_voucher r WHERE r.voucherDate between :startDate AND :endDate")
    BigDecimal testTotalCredit(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

    @Query(value = "SELECT COUNT(*) FROM ("
            + "SELECT 1 "
            + "FROM unresolved_voucher "
            + "WHERE voucher_date BETWEEN :startDate AND :endDate "
            + "GROUP BY voucher_date, voucher_number) AS grouped_vouchers",
            nativeQuery = true)
    BigDecimal journalTotalCount(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);
}
