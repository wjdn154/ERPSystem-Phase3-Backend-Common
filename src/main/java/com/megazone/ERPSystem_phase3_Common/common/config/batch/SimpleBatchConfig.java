package com.megazone.ERPSystem_phase3_Common.common.config.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class SimpleBatchConfig {

    // 기존 dynamic datasource 직접 주입해서 dataSource 연결
    // 추후 쓰기, tenant switching, public 으로 마지막에 바꾸기까지

    private final DataSource dynamicDataSource;

    public SimpleBatchConfig(DataSource dynamicDataSource) {
        this.dynamicDataSource = dynamicDataSource;
    }

    // JobLauncher 설정
    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
        // TaskExecutorJobLauncher를 사용하여 비동기 실행 지원
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jobRepository);  // JobRepository 설정
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor()); // 비동기 실행
        jobLauncher.afterPropertiesSet();  // 초기화
        return jobLauncher;
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
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
    public Job simpleJob(JobRepository jobRepository) {
        return new JobBuilder("simpleJob", jobRepository)
                .start(simpleStep(jobRepository))
                .build();
    }

    @Bean
    public Step simpleStep(JobRepository jobRepository) {
        return new StepBuilder("simpleStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("스프링 배치가 정상적으로 실행되었습니다!");
                    System.out.println("배치 작업 수행 중...");
                    return RepeatStatus.FINISHED; // Null 대신 RepeatStatus.FINISHED로 반환
                }, transactionManager())
                .build();
    }


}