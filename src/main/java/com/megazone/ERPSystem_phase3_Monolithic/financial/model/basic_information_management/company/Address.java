package com.megazone.ERPSystem_phase3_Monolithic.financial.model.basic_information_management.company;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 주소 정보 테이블
 * 회사등록 시 필요한 주소 데이터 테이블
 */
@Entity(name = "company_address")
@Table(name = "company_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id; // 고유 식별자

    @Column(nullable = false) // 사업장 우편번호
    private String businessPostalCode;

    @Column(nullable = false) // 사업장 주소
    private String businesseAddress;

    @Column(nullable = false) // 사업장 상세 주소
    private String businesseAddressDetail;

    @Column(nullable = false) // 사업장 신주소 여부
    private Boolean isBusinesseNewAddress;

    @Column(nullable = false) // 사업장 동 (예: 대연동)
    private String businessePlace;

    @Column(nullable = false) // 본점 우편 번호
    private String headquarterPostalCode;

    @Column(nullable = false) // 본점 주소
    private String headquarterAddress;

    @Column(nullable = false) // 본점 상세 주소
    private String headquarterAddressDetail;

    @Column(nullable = false) // 본점 신주소 여부
    private Boolean isHeadquarterNewAddress;

    @Column(nullable = false) // 본점 동
    private String headquarterPlace;

    public Address(String businessPostalCode, String businesseAddress, String businesseAddressDetail, Boolean isBusinesseNewAddress, String businessePlace, String headquarterPostalCode, String headquarterAddress, String headquarterAddressDetail, Boolean isHeadquarterNewAddress, String headquarterPlace) {
        this.businessPostalCode = businessPostalCode;
        this.businesseAddress = businesseAddress;
        this.businesseAddressDetail = businesseAddressDetail;
        this.isBusinesseNewAddress = isBusinesseNewAddress;
        this.businessePlace = businessePlace;
        this.headquarterPostalCode = headquarterPostalCode;
        this.headquarterAddress = headquarterAddress;
        this.headquarterAddressDetail = headquarterAddressDetail;
        this.isHeadquarterNewAddress = isHeadquarterNewAddress;
        this.headquarterPlace = headquarterPlace;
    }
}
