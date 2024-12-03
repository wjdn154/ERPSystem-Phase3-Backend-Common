package com.megazone.ERPSystem_phase3_Common.common.config.batch;

import com.megazone.ERPSystem_phase3_Common.Integrated.model.dashboard.dto.DashboardDataDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.service.dashboard.IntegratedService;
import com.megazone.ERPSystem_phase3_Common.Integrated.service.dashboard.S3DashboardService;
import com.megazone.ERPSystem_phase3_Common.common.config.multi_tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveDashboardToS3 implements Tasklet {

    private final IntegratedService integratedService;
    private final S3DashboardService s3DashboardService;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        // 배치 작업 중에 현재 테넌트 설정
        String tenantId = TenantContext.getCurrentTenant(); // 현재 테넌트 정보를 가져옴
        Authentication authentication = new UsernamePasswordAuthenticationToken(tenantId, null, List.of());  // 인증 정보 설정
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        try {
            DashboardDataDTO dashboardDataDTO = integratedService.dashboard();
            log.info("생성된 대시보드: {}", dashboardDataDTO);

            s3DashboardService.uploadDashboardData("dashboard-data.json", dashboardDataDTO);
            log.info("대시보드 데이터를 s3에 저장했습니다.");

        } catch (Exception e) {
            log.error("배치 작업 실행 중 오류 발생: ", e);
            throw e;
        } finally {
            // 배치 작업 후 인증 정보 초기화
            SecurityContextHolder.clearContext();
        }

        return RepeatStatus.FINISHED;
    }
}
