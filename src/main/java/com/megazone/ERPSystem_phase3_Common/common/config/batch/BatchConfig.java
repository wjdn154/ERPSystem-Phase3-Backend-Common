package com.megazone.ERPSystem_phase3_Common.common.config.batch;

import com.megazone.ERPSystem_phase3_Common.common.config.multi_tenant.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
// 추후 쓰기, tenant switching, public 으로 마지막에 바꾸기까지
@Configuration
@Slf4j
public class BatchConfig {
    private final SaveDashboardToS3 saveDashboardToS3;
    private final DataSource dynamicDataSource;

    public BatchConfig(DataSource dynamicDataSource, SaveDashboardToS3 saveDashboardToS3) {
        this.dynamicDataSource = dynamicDataSource;
        this.saveDashboardToS3 = saveDashboardToS3;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        log.info("TransactionManager 초기화 완료.");
        return new DataSourceTransactionManager(dynamicDataSource);
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dynamicDataSource);
        factory.setTransactionManager(transactionManager());
        factory.afterPropertiesSet();
        log.info("JobRepository가 성공적으로 초기화되었습니다.");
        return factory.getObject();
    }
    @Bean
    public TaskExecutor taskExecutor() {
        log.info("TaskExecutor 초기화 완료.");
        return new TenantAwareTaskExecutor();
    }

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(taskExecutor());
        jobLauncher.afterPropertiesSet();
        log.info("JobLauncher가 성공적으로 초기화되었습니다.");
        return jobLauncher;
    }


    // Test용 Simple Job
    @Bean
    public Job simpleJob(JobRepository jobRepository) {
        log.info("SimpleJob 생성 중...");
        return new JobBuilder("simpleJob", jobRepository)
                .start(simpleStep(jobRepository))
                .build();
    }

    @Bean
    public Step simpleStep(JobRepository jobRepository) {
        log.info("SimpleStep 생성 중...");
        return new StepBuilder("simpleStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("SimpleStep 실행 중...");
                    log.info("현재 테넌트: {}", TenantContext.getCurrentTenant());
                    return RepeatStatus.FINISHED;
                }, transactionManager())
                .build();
    }

    // Save Dashboard to S3 Job
    @Bean
    public Job saveDashboardToS3Job(JobRepository jobRepository) {
        log.info("saveDashboardToS3Job 생성 중...");
        return new JobBuilder("saveDashboardToS3Job", jobRepository)
                .start(saveToS3Step(jobRepository))
                .build();
    }

    @Bean
    public Step saveToS3Step(JobRepository jobRepository) {
        log.info("saveToS3Step 생성 중...");
        return new StepBuilder("saveToS3Step", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("SaveToS3 Step 실행 시작.");
                    saveDashboardToS3.execute(contribution, chunkContext);
                    log.info("SaveToS3 Step 실행 완료.");
                    return RepeatStatus.FINISHED;
                }, transactionManager())
                .build();
    }
}

//    @Bean
//    public JdbcCursorItemReader<BatchStepExecution> failedStepReader() {
//        JdbcCursorItemReader<BatchStepExecution> reader = new JdbcCursorItemReader<>();
//        reader.setDataSource(dynamicDataSource);
//        reader.setSql("SELECT STEP_EXECUTION_ID, STEP_NAME, STATUS, READ_COUNT, WRITE_COUNT "
//                + "FROM BATCH_STEP_EXECUTION WHERE STATUS = 'FAILED'");
//        reader.setRowMapper(new BeanPropertyRowMapper<>(BatchStepExecution.class));
//        return reader;
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<BatchStepExecution> stepExecutionUpdater() {
//        JdbcBatchItemWriter<BatchStepExecution> writer = new JdbcBatchItemWriter<>();
//        writer.setDataSource(dynamicDataSource);
//        writer.setSql("UPDATE BATCH_STEP_EXECUTION SET STATUS = :status, EXIT_MESSAGE = :exitMessage "
//                + "WHERE STEP_EXECUTION_ID = :stepExecutionId");
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//        return writer;
//    }
//}