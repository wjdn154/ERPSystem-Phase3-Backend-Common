package com.megazone.ERPSystem_phase3_Common.common.config.batch;

import com.megazone.ERPSystem_phase3_Common.common.config.multi_tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class S3BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job saveDashboardDataJob;
    private final Job readDashboardDataJob;
    String tenant = "tenant_1";


//    @Scheduled(cron = "0 0 * * * ?") // 매 시간마다 저장
    @Scheduled(cron = "*/5 * * * * ?") // 5초마다 실행
    public void runSaveDashboardDataJob() {


        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 현재 Tenant: " + TenantContext.getCurrentTenant());

            TenantContext.setCurrentTenant(tenant);
            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());

            // 배치 작업 실행
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis())
                    .addString("tenant", tenant)  // 테넌트 식별자를 JobParameters에 추가
                    .toJobParameters();

            System.out.println(">>> " + tenant + " 배치 작업 실행 시작");
            System.out.println(" saveDashboardDataJob: " + saveDashboardDataJob);
            System.out.println(" jobParameters: " + jobParameters);
            jobLauncher.run(saveDashboardDataJob, jobParameters);

            System.out.println(">>> " + tenant + " saveDashboardDataJob 배치 작업 실행 완료");

            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());

        } catch (Exception e) {
            System.err.println(">>> " + tenant + " runSaveDashboardDataJob 배치 작업 실행 중 오류 발생");
            e.printStackTrace();
        } finally {
            System.out.println(">>> 모든 배치 작업 완료 후, PUBLIC 테이블로 스위칭");
            TenantContext.setCurrentTenant("PUBLIC");
            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());

        }
    }
//
////    @Scheduled(cron = "0 30 * * * ?") // 매 시간 30분마다 읽기
//    @Scheduled(cron = "*/5 * * * * ?") // 5초마다 실행
//    public void runReadDashboardDataJob() {
//
//        try {
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//
//            TenantContext.setCurrentTenant(tenant);
//            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addLong("timestamp", System.currentTimeMillis())
//                    .addString("tenant", tenant)  // 테넌트 식별자를 JobParameters에 추가
//                    .toJobParameters();
//
//            System.out.println(">>> " + tenant + " 배치 작업 실행 시작");
//            System.out.println(" readDashboardDataJob: " + readDashboardDataJob);
//            System.out.println(" jobParameters: " + jobParameters);
//            jobLauncher.run(readDashboardDataJob, jobParameters);
//
//            System.out.println(">>> " + tenant + " readDashboardDataJob 배치 작업 실행 완료");
//
//            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//
//        } catch (Exception e) {
//            System.err.println(">>> " + tenant + " readDashboardDataJob 배치 작업 실행 중 오류 발생");
//            e.printStackTrace();
//        } finally {
//            System.out.println(">>> 모든 배치 작업 완료 후, PUBLIC 테이블로 스위칭");
//            TenantContext.setCurrentTenant("PUBLIC");
//            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//
//        }
//    }
}