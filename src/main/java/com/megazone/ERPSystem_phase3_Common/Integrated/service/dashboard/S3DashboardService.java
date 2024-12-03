package com.megazone.ERPSystem_phase3_Common.Integrated.service.dashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.DashboardDataDTO;
import com.megazone.ERPSystem_phase3_Common.common.config.S3Config;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class S3DashboardService {

    private final S3Config s3Config;
    private final ObjectMapper objectMapper;

    // S3에 JSON 데이터 저장
    public void uploadDashboardData(String key, DashboardDataDTO data) throws Exception {
        S3Client s3Client = s3Config.s3Client();
        String dashboardBucketName = s3Config.getDashboardBucketName();

        // 데이터를 JSON으로 직렬화
        String jsonData = objectMapper.writeValueAsString(data);
        InputStream inputStream = new ByteArrayInputStream(jsonData.getBytes(StandardCharsets.UTF_8));

        // S3 업로드
        try {
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(dashboardBucketName)
                            .key(key)
                            .build(),
                    RequestBody.fromInputStream(inputStream, jsonData.getBytes().length)
            );
        } catch (S3Exception e) {
            throw new RuntimeException("대시보드 S3 업로드 실패: " + e.awsErrorDetails().errorMessage());
        }
    }

        // S3에서 JSON 데이터 읽기
        public DashboardDataDTO fetchDashboardData (String key) throws Exception {
            S3Client s3Client = s3Config.s3Client();
            String bucketName = s3Config.getDashboardBucketName();

            // S3에서 데이터 가져오기
            try {
                InputStream inputStream = s3Client.getObject(
                        GetObjectRequest.builder()
                                .bucket(bucketName)
                                .key(key)
                                .build()
                );

                System.out.println(">>> inputStream = " + inputStream);
                return objectMapper.readValue(inputStream, DashboardDataDTO.class); // JSON 데이터를 DashboardDataDTO로 변환
            } catch (S3Exception e) {
                throw new RuntimeException("대시보드 S3 값 읽기 실패: " + e.awsErrorDetails().errorMessage());
            }
        }
    }