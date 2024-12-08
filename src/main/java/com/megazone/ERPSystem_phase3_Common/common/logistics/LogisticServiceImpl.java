package com.megazone.ERPSystem_phase3_Common.common.logistics;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class LogisticServiceImpl implements LogisticService {
    private final RestClient logisticsServiceClient;

    @Override
    public BigDecimal inventoryCount() {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        try {
            BigDecimal response =  logisticsServiceClient.post()
                    .uri("inventory/inventoryCount")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .body(BigDecimal.class);
            return response;
        }
        catch(Exception e) {
            throw new RuntimeException("재고 계산 서비스 호출 실패");
        }
    }
}
