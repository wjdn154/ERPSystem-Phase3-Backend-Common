package com.megazone.ERPSystem_phase3_Common.common.config.batch;

import com.megazone.ERPSystem_phase3_Common.common.config.multi_tenant.TenantContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

// 부모 스레드에서 설정된 테넌트 정보를 새로운 스레드로 복사
public class TenantAwareTaskExecutor extends ThreadPoolTaskExecutor {

    @Override
    public void execute(Runnable task) {
        String tenantId = TenantContext.getCurrentTenant();

        super.execute(() -> {
            try {
                TenantContext.setCurrentTenant(tenantId); // 자식 스레드에서 테넌트 설정
                System.out.println("TenantAwareTaskExecutor.execute - tenantId: " + tenantId);
                task.run();
            } finally {
                TenantContext.clear();
            }
        });
    }
}
