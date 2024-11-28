package com.megazone.ERPSystem_phase3_Common.Integrated.service.notification;

import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.Notification;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.dto.UserNotificationCreateAndSendDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.dto.UserNotificationSearchDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.dto.UserSubscriptionDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.enums.ModuleType;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.enums.NotificationType;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.enums.PermissionType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface NotificationService {
//    SseEmitter subscribe(Long employeeId, String tenantId, ModuleType module, PermissionType permission); // 사용자 구독 메서드
    Notification createAndSendNotification(UserNotificationCreateAndSendDTO requestData); // 알림 생성 및 전송 메서드
    void sendNotification(Notification notification, String tenantId); // 전체 사용자에게 알림 전송

    // 사용자 구독 관리
    // 사용자 구독 관리
    SseEmitter subscribe(Long employeeId, String tenantId, ModuleType module, PermissionType permission, String uuid);

    UserSubscriptionDTO getUserSubscriptionInfo(Long employeeId, boolean isAdmin);
//    void removeEmitter(Long employeeId);

    void removeEmitter(Long employeeId, String uuid);

    List<UserNotificationSearchDTO> createAndSearch(Long employeeId, ModuleType module, PermissionType permission);
    Long markAsRead(Long employeeId, Long notificationId);
}
