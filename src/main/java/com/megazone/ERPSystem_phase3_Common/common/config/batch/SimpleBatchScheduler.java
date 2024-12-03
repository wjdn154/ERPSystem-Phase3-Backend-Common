package com.megazone.ERPSystem_phase3_Common.common.config.batch;

import com.megazone.ERPSystem_phase3_Common.common.config.database.DataSourceConfig;
import com.megazone.ERPSystem_phase3_Common.common.config.database.DataSourceContext;
import com.megazone.ERPSystem_phase3_Common.common.config.multi_tenant.TenantContext;
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
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SimpleBatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job simpleJob;
    private final DataSource dynamicDataSource;

    // 구현 단계
    // 1. tenant_1 만으로 일단 처리 <- 테넌트 지정해도 계속 tenant_2에만 저장 중임
    // 2. s3로 DashBoardDataDTO를 바로 전달해서 저장
    // 3. s3에서 클라이언트에 json 형태로 전달해 보여주기
    // 4. 제일 마지막에 테넌트별 반복 ( 현재는 단일로만 진행 )

    @Scheduled(cron = "*/5 * * * * ?") // 5초마다 실행
    public void runSimpleJob() {
        String tenant = "tenant_1";

        try {
            TenantContext.setCurrentTenant(tenant);
            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());

            // 배치 작업 실행
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis())
                    .addString("tenant", tenant)  // 테넌트 식별자를 JobParameters에 추가
                    .toJobParameters();

            System.out.println(">>> " + tenant + " 배치 작업 실행 시작");
            System.out.println(">>>>>>>>>>> " + " simpleJob: " + simpleJob);
            System.out.println(">>>>>>>>>>> " + " jobParameters: " + jobParameters);
            jobLauncher.run(simpleJob, jobParameters);

            System.out.println(">>> " + tenant + " 배치 작업 실행 완료");

            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());

        } catch (Exception e) {
            System.err.println(">>> " + tenant + " 배치 작업 실행 중 오류 발생");
            e.printStackTrace();
        } finally {
            System.out.println(">>> 모든 배치 작업 완료 후, PUBLIC 테이블로 스위칭");
            TenantContext.setCurrentTenant("PUBLIC");
            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());

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

}