package com.megazone.ERPSystem_phase3_Common.common.production;

import com.megazone.ERPSystem_phase3_Common.common.production.dto.CalculatorDTO;
import com.megazone.ERPSystem_phase3_Common.common.production.dto.CalculatorResponseDTO;
import com.megazone.ERPSystem_phase3_Common.common.production.dto.ProductionCalculatorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductionServiceImpl implements ProductionService {

    private final RestClient productionServiceClient;

    @Override
    public Integer wasteScoreCalculator(ProductionCalculatorDTO requestData) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        try {
            Integer response =  productionServiceClient.post()
                    .uri("productionOrder/calculator/wasteScore")
                    .header("Authorization", "Bearer " + token)
                    .body(requestData)
                    .retrieve()
                    .body(Integer.class);
            if(response == null) {
                throw new RuntimeException("폐기물 점수게산 API null");
            }
            return response;
        }
        catch(Exception e) {
            throw new RuntimeException("폐기물 점수계산 서비스 호출 실패");
        }
    }

    @Override
    public Integer energyScoreCalculator(ProductionCalculatorDTO requestData) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        try {
            Integer response =  productionServiceClient.post()
                    .uri("productionOrder/calculator/energyScore")
                    .header("Authorization", "Bearer " + token)
                    .body(requestData)
                    .retrieve()
                    .body(Integer.class);
            if(response == null) {
                throw new RuntimeException("에너지 점수게산 API null");
            }
            return response;
        }
        catch(Exception e) {
            throw new RuntimeException("에너지 점수계산 서비스 호출 실패");
        }
    }

    @Override
    public CalculatorResponseDTO ScoreAllCalculator(CalculatorDTO requestData) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        try {
            CalculatorResponseDTO response =  productionServiceClient.post()
                    .uri("productionOrder/calculator/scoreAll")
                    .header("Authorization", "Bearer " + token)
                    .body(requestData)
                    .retrieve()
                    .body(CalculatorResponseDTO.class);
            if(response == null) {
                throw new RuntimeException("에너지 점수 전체 계산 API null");
            }
            return response;
        }
        catch(Exception e) {
            throw new RuntimeException("에너지 점수 전체계산 서비스 호출 실패");
        }
    }

    @Override
    public BigDecimal totalWorkPerformance() {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        try {
            BigDecimal totalWorkPerformance = productionServiceClient.post()
                    .uri("workPerformance/totalWorkPerformance")
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .body(BigDecimal.class);
            return totalWorkPerformance;
        }
        catch(Exception e) {
            throw new RuntimeException("작업 실적 서비스 호출 실패");
        }
    }
}
