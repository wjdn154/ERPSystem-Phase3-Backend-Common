package com.megazone.ERPSystem_phase3_Common.common.config.batch;

import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.DashboardDataDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.service.dashboard.IntegratedService;
import com.megazone.ERPSystem_phase3_Common.Integrated.service.dashboard.S3DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveDashboardToS3 implements Tasklet {

    private final IntegratedService integratedService;
    private final S3DashboardService s3DashboardService;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        DashboardDataDTO dashboardDataDTO = integratedService.dashboard();
        log.info("생성된 대시보드: {}", dashboardDataDTO);

        s3DashboardService.uploadDashboardData("dashboard-data.json", dashboardDataDTO);
        log.info("대시보드 데이터를 s3에 저장했습니다.");

        return RepeatStatus.FINISHED;
    }
}
