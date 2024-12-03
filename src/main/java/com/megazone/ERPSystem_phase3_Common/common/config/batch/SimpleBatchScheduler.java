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

            // 출력: 현재 활성화된 테넌트 확인
            printActiveTenant();

            // 출력: tenant_1 / tenant_2에 해당하는 배치 테이블 데이터 확인
            printBatchJobExecutionData();

            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();

            System.out.println(">>> 배치 작업 실행 시작");
            jobLauncher.run(simpleJob, jobParameters);
            System.out.println(">>> 배치 작업 실행 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 현재 활성화된 테넌트 데이터소스 확인
    private void printActiveTenant() {
        try (Connection connection = dynamicDataSource.getConnection()) {
            String schemaName = connection.getCatalog(); // 현재 데이터베이스 스키마 이름을 가져옴
            System.out.println(">>> 현재 활성화된 테넌트 스키마: " + schemaName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 배치 테이블의 데이터 확인 (tenant_1 / tenant_2)
    private void printBatchJobExecutionData() {
        try (Connection connection = dynamicDataSource.getConnection()) {
            // batch_job_execution 테이블에서 데이터가 있는지 확인
            String sql = "SELECT COUNT(*) FROM BATCH_JOB_EXECUTION";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1); // 데이터의 개수를 가져옴
                    System.out.println(">>> 현재 BATCH_JOB_EXECUTION 테이블의 데이터 개수: " + count);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}