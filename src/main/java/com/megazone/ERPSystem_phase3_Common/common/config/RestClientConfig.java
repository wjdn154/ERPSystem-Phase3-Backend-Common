package com.megazone.ERPSystem_phase3_Common.common.config;

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
                .baseUrl("http://localhost:8081/api/production/")
                .build();
    }

    @Bean
    public RestClient logisticsServiceClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8081/api/logistics/")
                .build();
    }

    @Bean
    public RestClient HumanResourceServiceClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8080/api/hr/")
                .build();
    }

    @Bean
    public RestClient financialServiceClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8080/api/financial/")
                .build();
    }
}
