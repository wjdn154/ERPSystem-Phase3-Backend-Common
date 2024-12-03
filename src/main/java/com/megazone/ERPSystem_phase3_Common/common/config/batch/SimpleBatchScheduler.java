package com.megazone.ERPSystem_phase3_Common.common.config.batch;

import com.megazone.ERPSystem_phase3_Common.common.config.database.DataSourceConfig;
import com.megazone.ERPSystem_phase3_Common.common.config.database.DataSourceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SimpleBatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job simpleJob;
    private final DataSource dynamicDataSource;

    @Scheduled(cron = "*/5 * * * * ?") // 5초마다 실행
    public void runSimpleJob() {
        try {
            List<String> tenants = getAllTenants();  // DB에서 테넌트 목록을 조회
            for (String tenant : tenants) {
                try {
                    DataSourceContext.setCurrentDataSource(tenant); // 테넌트 데이터소스 설정

//                    printActiveTenant();
//                    printBatchJobExecutionData();
                    // 배치 작업 실행
                    JobParameters jobParameters = new JobParametersBuilder()
                            .addLong("timestamp", System.currentTimeMillis())
                            .addString("tenant", tenant)  // 테넌트 식별자를 JobParameters에 추가
                            .toJobParameters();

                    System.out.println(">>> " + tenant + " 배치 작업 실행 시작");
                    jobLauncher.run(simpleJob, jobParameters);
                    System.out.println(">>> " + tenant + " 배치 작업 실행 완료");

                } catch (Exception e) {
                    System.err.println(">>> " + tenant + " 배치 작업 실행 중 오류 발생");
                    e.printStackTrace();
                } finally {
                    DataSourceContext.clear(); // 각 테넌트 작업 후 데이터 소스 컨텍스트 초기화
                }
            }

            System.out.println(">>> 모든 배치 작업 완료 후, PUBLIC 테이블로 스위칭");
            DataSourceContext.setCurrentDataSource("PUBLIC"); // 모든 배치가 끝난 후, 마지막에는 PUBLIC 테이블로 스위칭


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 모든 테넌트 목록을 조회하는 메서드
    private List<String> getAllTenants() {
        List<String> tenants = new ArrayList<>();
        String sql = "SELECT id FROM company";

        try (Connection connection = dynamicDataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
            // 결과셋에서 각 company id를 가져와서 'tenant_' 접두사를 붙여서 목록에 추가
            while (resultSet.next()) {
                long companyId = resultSet.getLong("id");  // 'id'는 Long 타입
                String tenantSchema = "tenant_" + companyId;  // 'tenant_'와 company id 결합

                // 스키마가 존재하는지 확인 (tenant_1, tenant_2만 존재한다고 가정)
                if (isSchemaExists(tenantSchema)) {
                    tenants.add(tenantSchema);  // 유효한 테넌트 스키마만 추가
                }
            }
        } catch (Exception e) {
            System.out.println(">>> Tenant 목록 조회 중 오류 발생");
            e.printStackTrace();
        }

        return tenants;
    }

    // 스키마가 존재하는지 확인하는 메서드
    private boolean isSchemaExists(String schemaName) {
        String checkSchemaSql = "SELECT 1 FROM information_schema.schemata WHERE schema_name = ?";
        try (Connection connection = dynamicDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(checkSchemaSql)) {

            statement.setString(1, schemaName);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();  // 해당 스키마가 존재하면 true 반환
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //    // 현재 활성화된 테넌트 데이터소스 확인
//    private void printActiveTenant() {
//        try (Connection connection = dynamicDataSource.getConnection()) {
//            String schemaName = connection.getCatalog(); // 현재 데이터베이스 스키마 이름을 가져옴
//            System.out.println(">>> 현재 활성화된 테넌트 스키마: " + schemaName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 배치 테이블의 데이터 확인 (tenant_1 / tenant_2)
//    private void printBatchJobExecutionData() {
//        try (Connection connection = dynamicDataSource.getConnection()) {
//            String sql = "SELECT COUNT(*) FROM BATCH_JOB_EXECUTION";             // batch_job_execution 테이블에서 데이터가 있는지 확인
//            try (PreparedStatement statement = connection.prepareStatement(sql);
//                 ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    int count = resultSet.getInt(1); // 데이터의 개수를 가져옴
//                    System.out.println(">>> 현재 BATCH_JOB_EXECUTION 테이블의 데이터 개수: " + count);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}