package com.megazone.ERPSystem_phase3_Common.company.controller.basic_information_management.company;

import com.megazone.ERPSystem_phase3_Common.company.model.basic_information_management.company.dto.CompanyDTO;
import com.megazone.ERPSystem_phase3_Common.company.service.basic_information_management.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/common/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    /**
     * 회사 조회
     * @return 조회한 회사 정보를 담은 CompanyDTO 객체를 반환함.
     */
    @PostMapping("/")
    public ResponseEntity<List<CompanyDTO>> findCompany() {
        List<CompanyDTO> allCompany = companyService.findAllCompany();
        return ResponseEntity.ok(allCompany);
    }

    /**
     * 회사 상세 조회
     * @param searchText 조회할 회사의 검색어
     */
    @PostMapping("/search")
    public List<CompanyDTO> searchCompany(@RequestBody Map<String, String> searchText) {
        return companyService.searchCompany(searchText.get("searchText"));
    }

    /**
     * 회사 등록
     * @return 등록한 회사 정보를 담은 CompanyDTO 객체를 반환함.
     */
    @PostMapping("/save")
    public ResponseEntity<CompanyDTO> saveCompany(@RequestBody CompanyDTO companyDTO) {
        Optional<CompanyDTO> savedCompany = companyService.saveCompany(companyDTO);
        return savedCompany
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    /**
     * 회사 수정
     * @param id 수정할 회사의 ID
     * @param companyDTO 수정할 회사 정보가 담긴 DTO
     * @return 수정된 회사 정보를 담은 CompanyDTO 객체를 반환함.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable("id") Long id, @RequestBody CompanyDTO companyDTO) {
        Optional<CompanyDTO> updatedCompany = companyService.updateCompany(id, companyDTO);
        return updatedCompany
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}