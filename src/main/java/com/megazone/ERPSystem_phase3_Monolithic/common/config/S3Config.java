package com.megazone.ERPSystem_phase3_Monolithic.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    private String accessKey = "AKIA4DMVQZBRPKQIV462";

    private String secretKey = "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";

    private String region = "ap-northeast-2";

//    @Value("${AWS_ACCESS_KEY_ID}")
//    private String accessKey;
//
//    @Value("${AWS_SECRET_ACCESS_KEY}")
//    private String secretKey;
//
//    @Value("${AWS_REGION}")
//    private String region;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }
}