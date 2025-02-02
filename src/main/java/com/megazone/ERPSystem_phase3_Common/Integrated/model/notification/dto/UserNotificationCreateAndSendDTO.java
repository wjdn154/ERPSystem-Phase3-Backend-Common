package com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.dto;

import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.enums.ModuleType;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.enums.NotificationType;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.enums.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotificationCreateAndSendDTO {
    private ModuleType moduleType;
    private PermissionType permissionType;
    private String notificationDescription;
    private NotificationType notificationType;
}
