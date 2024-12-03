package com.megazone.ERPSystem_phase3_Common.common.config.batch;

import com.megazone.ERPSystem_phase3_Common.common.config.multi_tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class S3DashboardConfig {

    private final LoadDashboardFromS3 loadDashboardFromS3;
    private final SaveDashboardToS3 saveDashboardToS3;
    private final DataSource dynamicDataSource;

    @Bean
    public TaskExecutor taskExecutor() {
        return new TenantAwareTaskExecutor();

    }

    // JobLauncher 설정
    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {

        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher(); // 비동기 실행 지원
        jobLauncher.setJobRepository(jobRepository);  // JobRepository 설정
        jobLauncher.setTaskExecutor(taskExecutor()); // 메서드 호출을 통한 TenantAwareTaskExecutor Bean 주입
        jobLauncher.afterPropertiesSet();  // 초기화
        return jobLauncher;
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
        TenantContext.setCurrentTenant("PUBLIC");
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dynamicDataSource);
        factory.setTransactionManager(transactionManager());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

    @Bean
    public Job saveDashboardDataJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("saveDashboardDataJob", jobRepository)
                .start(saveToS3Step(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Job readDashboardDataJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("readDashboardDataJob", jobRepository)
                .start(loadFromS3Step(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step saveToS3Step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("saveToS3Step", jobRepository)
                .tasklet(saveDashboardToS3, transactionManager)
                .build();
    }

    @Bean
    public Step loadFromS3Step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("loadFromS3Step", jobRepository)
                .tasklet(loadDashboardFromS3, transactionManager)
                .build();
    }
}
