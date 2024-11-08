package com.megazone.ERPSystem_phase3_Monolithic.Integrated.model.notification.dto;

import com.megazone.ERPSystem_phase3_Monolithic.Integrated.model.notification.Notification;
import com.megazone.ERPSystem_phase3_Monolithic.Integrated.model.notification.UserNotification;
import com.megazone.ERPSystem_phase3_Monolithic.Integrated.model.notification.enums.ModuleType;
import com.megazone.ERPSystem_phase3_Monolithic.Integrated.model.notification.enums.NotificationType;
import com.megazone.ERPSystem_phase3_Monolithic.Integrated.model.notification.enums.PermissionType;
import com.megazone.ERPSystem_phase3_Monolithic.hr.model.basic_information_management.employee.Users;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserNotificationDTO {
    private Long userId;
    private Notification notification;
    private ModuleType module;
    private PermissionType permission;
    private NotificationType type;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime readAt;
    private boolean readStatus;
}
