package com.megazone.ERPSystem_phase3_Common.financial.repository.basic_information_management.client;

import com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.client.dto.fetchClientListDTO;

import java.util.List;

public interface ClientRepositoryCustom {
    List<fetchClientListDTO> fetchClientList();
}