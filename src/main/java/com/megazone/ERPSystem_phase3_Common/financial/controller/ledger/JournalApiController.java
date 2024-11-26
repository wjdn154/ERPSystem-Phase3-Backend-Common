package com.megazone.ERPSystem_phase3_Common.financial.controller.ledger;


import com.megazone.ERPSystem_phase3_Common.financial.model.ledger.dto.JournalDTO;
import com.megazone.ERPSystem_phase3_Common.financial.model.ledger.dto.JournalShowDTO;
import com.megazone.ERPSystem_phase3_Common.financial.model.ledger.dto.JournalShowDetailDTO;
import com.megazone.ERPSystem_phase3_Common.financial.service.ledger.JournalService;
import com.megazone.ERPSystem_phase3_Common.hr.repository.basic_information_management.Users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

// 분개장
@RestController
@RequiredArgsConstructor
public class JournalApiController {
    private final JournalService journalService;
    private final UsersRepository usersRepository;

    @PostMapping("/api/financial/ledger/journal/show")
    public ResponseEntity<JournalShowDTO> journalShow(@RequestBody JournalDTO dto) {

        List<JournalShowDetailDTO> ShowDTOs = journalService.journalSearch(dto.getStartDate(),dto.getEndDate())
                .stream().map(JournalShowDetailDTO::create).toList();

        List<BigDecimal> totalAmounts = journalService.journalTotalAmount(dto.getStartDate(),dto.getEndDate());
        BigDecimal totalCount = journalService.journalTotalCount(dto.getStartDate(),dto.getEndDate());

        JournalShowDTO journalShowDTO = JournalShowDTO.create(ShowDTOs,totalAmounts.get(0),totalAmounts.get(1),
                totalCount);

        return journalShowDTO != null ?
                ResponseEntity.status(HttpStatus.OK).body(journalShowDTO) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
