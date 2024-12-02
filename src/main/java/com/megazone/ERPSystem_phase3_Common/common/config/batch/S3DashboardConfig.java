//package com.megazone.ERPSystem_phase3_Common.common.config.batch;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@EnableBatchProcessing
//@RequiredArgsConstructor
//public class S3DashboardConfig {
//
//    private final LoadDashboardFromS3 loadDashboardFromS3;
//    private final SaveDashboardToS3 saveDashboardToS3;
//
//    @Bean
//    public Job saveDashboardDataJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobBuilder("saveDashboardDataJob", jobRepository)
//                .start(saveToS3Step(jobRepository, transactionManager))
//                .build();
//    }
//
//    @Bean
//    public Job readDashboardDataJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new JobBuilder("readDashboardDataJob", jobRepository)
//                .start(loadFromS3Step(jobRepository, transactionManager))
//                .build();
//    }
//
//    @Bean
//    public Step saveToS3Step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("saveToS3Step", jobRepository)
//                .tasklet(saveDashboardToS3, transactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step loadFromS3Step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("loadFromS3Step", jobRepository)
//                .tasklet(loadDashboardFromS3, transactionManager)
//                .build();
//    }
//}
