package com.megazone.ERPSystem_phase3_Common.common.config.batch;

import com.megazone.ERPSystem_phase3_Common.common.config.multi_tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.UUID;


// 구현 단계
// 1. 테넌트 지정 없이 임시로 현재 활성화된 테넌트 스키마에 배치 처리
// 2. s3로 DashBoardDataDTO를 바로 전달해서 저장
// 3. s3에서 클라이언트에 json 형태로 전달해 보여주기
// 4. 위 단계 완료 후에 추후 테넌트 스위칭
@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job simpleJob;
    private final Job saveDashboardToS3Job;

//    @Scheduled(cron = "*/5 * * * * ?") // 5초마다 실행
//    public void runSimpleJob() {
//
//        try {
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//
//            // 배치 작업 실행
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addLong("timestamp", System.currentTimeMillis())
//                    .toJobParameters();
//
//            System.out.println(">>> " + " 배치 작업 실행 시작");
//            System.out.println(" simpleJob: " + simpleJob);
//            System.out.println(" jobParameters: " + jobParameters);
//            jobLauncher.run(simpleJob, jobParameters);
//
//            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//
//        } catch (Exception e) {
//            System.err.println(">>> " + " 배치 작업 실행 중 오류 발생");
//            e.printStackTrace();
//        } finally {
//            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
//        }
//    }

//    @Scheduled(cron = "0 0 * * * ?") // 매 시간마다 저장
//    @Scheduled(cron = "*/10 * * * * ?") // Test를 위해 10초마다 실행
    @Scheduled(fixedDelay = 10000)  // Test를 위해 10초 간격으로 실행
    public void runSaveDashboardToS3Job() {
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> runSaveDashboardToS3Job TRY");
//            TenantContext.setCurrentTenant("tenant_1");
            System.out.println(">>> 현재 Tenant: " + TenantContext.getCurrentTenant());
            String uniqueJobKey = "save_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString();

            // 배치 작업 실행
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("uniqueJobKey", uniqueJobKey)
                    .toJobParameters();

            System.out.println(" saveDashboardToS3Job: " + saveDashboardToS3Job);
            System.out.println(" jobParameters: " + jobParameters);
            jobLauncher.run(saveDashboardToS3Job, jobParameters);
            log.info("JobLauncher 실행: {}", saveDashboardToS3Job.getName(), saveDashboardToS3Job.getJobParametersIncrementer());



        } catch (Exception e) {
            System.err.println(">>> " + " runSaveDashboardToS3Job 배치 작업 실행 중 오류 발생");
            e.printStackTrace();
        } finally {
            TenantContext.setCurrentTenant("PUBLIC");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> runSaveDashboardToS3Job FINALE");
        }
    }

    // Logging 용
    @Component
    public class StepCompletionListener extends StepExecutionListenerSupport {
        @Override
        public void beforeStep(StepExecution stepExecution) {
            log.info("Step 시작: {}", stepExecution.getStepName());
        }

        @Override
        public ExitStatus afterStep(StepExecution stepExecution) {
            log.info("Step 종료: {}", stepExecution.getStepName());
            return stepExecution.getExitStatus();
        }
    }

}
