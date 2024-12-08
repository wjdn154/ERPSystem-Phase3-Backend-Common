package com.megazone.ERPSystem_phase3_Common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    private final SecretManagerConfig secretManagerConfig;

    @Bean
    public RestClient productionServiceClient() {
        return RestClient.builder()
                .baseUrl(secretManagerConfig.getProductionServiceUrl())
                .build();
    }

    @Bean
    public RestClient logisticsServiceClient() {
        return RestClient.builder()
                .baseUrl(secretManagerConfig.getLogisticsServiceUrl())
                .build();
    }

    @Bean
    public RestClient HumanResourceServiceClient() {
        return RestClient.builder()
                .baseUrl(secretManagerConfig.getHumanResourceServiceUrl())
                .build();
    }

    @Bean
    public RestClient financialServiceClient() {
        return RestClient.builder()
                .baseUrl(secretManagerConfig.getFinancialServiceUrl())
                .build();
    }
}
