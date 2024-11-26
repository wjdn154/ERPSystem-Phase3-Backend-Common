package com.megazone.ERPSystem_phase3_Common.financial.service.basic_information_management.client;

import com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.client.dto.CategoryDTO;
import com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.client.dto.ClientDTO;
import com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.client.dto.LiquorDTO;
import com.megazone.ERPSystem_phase3_Common.financial.model.common.dto.BankDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    Long saveClient(ClientDTO clientDTO);
    ClientDTO updateClient(ClientDTO clientDTO);
    ResponseEntity<Object> fetchClientList();
    ResponseEntity<Object> searchClientList();
    ResponseEntity<Object> fetchClient(Long id);
    List<LiquorDTO> fetchLiquorList();
    List<CategoryDTO> fetchCategoryList();
    List<BankDTO> fetchBankList();
}