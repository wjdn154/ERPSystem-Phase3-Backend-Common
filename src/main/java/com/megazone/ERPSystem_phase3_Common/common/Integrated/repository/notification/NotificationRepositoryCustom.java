package com.megazone.ERPSystem_phase3_Common.common.Integrated.repository.notification;

import com.megazone.ERPSystem_phase3_Common.common.Integrated.model.notification.dto.UserNotificationDTO;
import com.megazone.ERPSystem_phase3_Common.common.Integrated.model.notification.enums.ModuleType;
import com.megazone.ERPSystem_phase3_Common.common.Integrated.model.notification.enums.PermissionType;

import java.util.List;

public interface NotificationRepositoryCustom {
    List<UserNotificationDTO> fetchNotification(Long userId, ModuleType module, PermissionType permission);
}
