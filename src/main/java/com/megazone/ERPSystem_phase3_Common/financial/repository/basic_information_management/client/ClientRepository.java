package com.megazone.ERPSystem_phase3_Common.financial.repository.basic_information_management.client;

import com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.client.Client;
import com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.client.dto.searchClientListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long>, ClientRepositoryCustom {

    Optional<Client> findByCode(String code);

    @Query("SELECT MAX(c.code) FROM client c")
    String findMaxCode();

    @Query("SELECT new com.megazone.ERPSystem_phase3_Common.financial.model.basic_information_management.client.dto.searchClientListDTO(c.id, c.printClientName) " +
            "FROM client c")
    List<searchClientListDTO> searchClientList();
}