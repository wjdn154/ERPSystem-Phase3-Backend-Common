package com.megazone.ERPSystem_phase3_Common.common.config.batch;

import com.megazone.ERPSystem_phase3_Common.common.config.database.DataSourceConfig;
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

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SimpleBatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job simpleJob;
//    private final BatchSchemaInitializer batchSchemaInitializer;
    private final DataSource dynamicDataSource;

    @Scheduled(cron = "*/5 * * * * ?") // 5초마다 실행
    public void runSimpleJob() {
        try {
            // 현재 활성화된 테넌트의 스키마에 Batch 테이블 생성
//            batchSchemaInitializer.initializeBatchSchema(dynamicDataSource);

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
}