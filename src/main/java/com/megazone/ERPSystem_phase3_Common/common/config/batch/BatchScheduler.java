//package com.megazone.ERPSystem_phase3_Common.common.config.batch;
//
//import com.megazone.ERPSystem_phase3_Common.common.config.multi_tenant.TenantContext;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableScheduling
//@RequiredArgsConstructor
//public class BatchScheduler {
//
//    private final JobLauncher jobLauncher;
//    private final Job simpleJob;
//
//    // 구현 단계
//    // 1. tenant_1 만으로 일단 처리 <- 테넌트 지정해도 계속 tenant_2에만 저장 중임
//    // 2. s3로 DashBoardDataDTO를 바로 전달해서 저장
//    // 3. s3에서 클라이언트에 json 형태로 전달해 보여주기
//
//    @Scheduled(cron = "*/5 * * * * ?") // 5초마다 실행
//    public void runSimpleJob() {
//        String tenant = "PUBLIC";
//
//        try {
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//
//
//            TenantContext.setCurrentTenant(tenant);
//            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//
//            // 배치 작업 실행
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addLong("timestamp", System.currentTimeMillis())
//                    .addString("tenant", tenant)  // 테넌트 식별자를 JobParameters에 추가
//                    .toJobParameters();
//
//            System.out.println(">>> " + tenant + " 배치 작업 실행 시작");
//            System.out.println(" simpleJob: " + simpleJob);
//            System.out.println(" jobParameters: " + jobParameters);
//            jobLauncher.run(simpleJob, jobParameters);
//
//            System.out.println(">>> " + tenant + " 배치 작업 실행 완료");
//
//            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//
//        } catch (Exception e) {
//            System.err.println(">>> " + tenant + " 배치 작업 실행 중 오류 발생");
//            e.printStackTrace();
//        } finally {
//            System.out.println(">>> 모든 배치 작업 완료 후, PUBLIC 테이블로 스위칭");
//            TenantContext.setCurrentTenant("PUBLIC");
//            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//
//        }
//    }
//}