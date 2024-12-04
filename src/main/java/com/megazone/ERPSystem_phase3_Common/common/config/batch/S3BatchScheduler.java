//package com.megazone.ERPSystem_phase3_Common.common.config.batch;
//
//import com.megazone.ERPSystem_phase3_Common.common.config.multi_tenant.TenantContext;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.explore.JobExplorer;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.util.UUID;
//
//@Configuration
//@EnableScheduling
//@RequiredArgsConstructor
//public class S3BatchScheduler {
//
//    private final JobLauncher jobLauncher;
//    private final Job saveDashboardDataJob;
//    private final Job readDashboardDataJob;
//    String tenant = "tenant_2"; // dataSource 연결 후 마지막 활성화된 테넌트 스키마로 지정하여 테스트
//
////    @Scheduled(cron = "0 0 * * * ?") // 매 시간마다 저장
////    @Scheduled(cron = "*/10 * * * * ?") // Test를 위해 10초마다 실행
//    @Scheduled(fixedDelay = 10000)  // Test를 위해 10초 간격으로 실행
//    public void runSaveDashboardDataJob() {
//        try {
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//
//            TenantContext.setCurrentTenant(tenant);
//            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//
//            String uniqueJobKey = "save_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString();
//
//            // 배치 작업 실행
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addString("tenant", tenant)
////                    .addLong("timestamp", System.currentTimeMillis())
//                    .addString("uniqueJobKey", uniqueJobKey)
//                    .toJobParameters();
//
//            System.out.println(">>> " + tenant + " 배치 작업 실행 시작");
//            System.out.println(" saveDashboardDataJob: " + saveDashboardDataJob);
//            System.out.println(" jobParameters: " + jobParameters);
//
////            JobExecution lastExecution = jobExplorer.getLastJobExecution("saveDashboardDataJob", jobParameters);
////            if (lastExecution != null && (lastExecution.isRunning() || lastExecution.getStatus() == BatchStatus.FAILED)) {
////                System.err.println("Job is already running or failed. Skipping execution.");
////                return;
////            }
////            jobLauncher.run(saveDashboardDataJob, jobParameters);
//
//
//            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//
//        } catch (Exception e) {
//            System.err.println(">>> " + tenant + " runSaveDashboardDataJob 배치 작업 실행 중 오류 발생");
//            e.printStackTrace();
//        }
//    }
//
//////    @Scheduled(cron = "0 30 * * * ?") // 매 시간 30분마다 읽기
////    @Scheduled(cron = "*/5 * * * * ?") // Test를 위해 5초마다 실행
////    public void runReadDashboardDataJob() {
////
////        try {
////            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 현재 Tenant: " + TenantContext.getCurrentTenant());
////
////            TenantContext.setCurrentTenant(tenant);
////            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
////
////            String uniqueJobKey = "read_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString();
////
////            JobParameters jobParameters = new JobParametersBuilder()
//////                    .addLong("timestamp", System.currentTimeMillis())
////                    .addString("tenant", tenant)
////                    .addString("uniqueJobKey", uniqueJobKey)
////                    .toJobParameters();
////
////            System.out.println(">>> " + tenant + " 배치 작업 실행 시작");
////            System.out.println(" readDashboardDataJob: " + readDashboardDataJob);
////            System.out.println(" jobParameters: " + jobParameters);
////            jobLauncher.run(readDashboardDataJob, jobParameters);
////
////            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
////
////        } catch (Exception e) {
////            System.err.println(">>> " + tenant + " readDashboardDataJob 배치 작업 실행 중 오류 발생");
////            e.printStackTrace();
////        } finally {
////            TenantContext.setCurrentTenant("PUBLIC");
////            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
////
////        }
////    }
//}