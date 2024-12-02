//package com.megazone.ERPSystem_phase3_Common.common.config.batch;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//@Configuration
//@EnableScheduling
//@RequiredArgsConstructor
//public class S3BatchScheduler {
//
//    private final JobLauncher jobLauncher;
//    private final Job saveDashboardDataJob;
//    private final Job readDashboardDataJob;
//
//    @Scheduled(cron = "0 0 * * * ?") // 매 시간마다 저장
//    public void runSaveDashboardDataJob() {
//        try {
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addLong("timestamp", System.currentTimeMillis())
//                    .toJobParameters();
//            jobLauncher.run(saveDashboardDataJob, jobParameters);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Scheduled(cron = "0 30 * * * ?") // 매 시간 30분마다 읽기
//    public void runReadDashboardDataJob() {
//        try {
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addLong("timestamp", System.currentTimeMillis())
//                    .toJobParameters();
//            jobLauncher.run(readDashboardDataJob, jobParameters);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}