package com.megazone.ERPSystem_phase3_Common.common.Integrated.repository.notification;
import com.megazone.ERPSystem_phase3_Common.common.Integrated.model.notification.QNotification;
import com.megazone.ERPSystem_phase3_Common.common.Integrated.model.notification.QUserNotification;
import com.megazone.ERPSystem_phase3_Common.common.Integrated.model.notification.dto.UserNotificationDTO;
import com.megazone.ERPSystem_phase3_Common.common.Integrated.model.notification.enums.ModuleType;
import com.megazone.ERPSystem_phase3_Common.common.Integrated.model.notification.enums.PermissionType;
import com.megazone.ERPSystem_phase3_Common.common.user.model.basic_information_management.employee.QUsers;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<UserNotificationDTO> fetchNotification(Long userId, ModuleType module, PermissionType permission) {
        QNotification qNotification = QNotification.notification;
        QUserNotification qUserNotification = QUserNotification.userNotification;
        QUsers qUsers = QUsers.users;

        // 조건 빌더 설정
        BooleanBuilder builder = new BooleanBuilder();
        if (module != ModuleType.ALL || permission != PermissionType.ALL) {
            builder.or(qNotification.module.eq(module).and(qNotification.permission.eq(permission)))
                   .or(qNotification.module.eq(ModuleType.ALL).and(qNotification.permission.eq(permission)))
                   .or(qNotification.module.eq(module).and(qNotification.permission.eq(PermissionType.ALL)))
                   .or(qNotification.module.eq(ModuleType.ALL).and(qNotification.permission.eq(PermissionType.ALL)));
        }

        return queryFactory
                .select(Projections.constructor(UserNotificationDTO.class,
                        qUsers.id.as("userId"),
                        qNotification.as("notification"),
                        qNotification.module.as("module"),
                        qNotification.permission.as("permission"),
                        qNotification.type.as("type"),
                        qNotification.content.as("content"),
                        qNotification.createAt.as("createAt"),
                        qUserNotification.readAt.as("readAt"),
                        qUserNotification.readStatus.as("readStatus")
                ))
                .from(qNotification)
                .leftJoin(qUserNotification).on(qUserNotification.notification.eq(qNotification).and(qUserNotification.userId.eq(userId)))
                .leftJoin(qUsers).on(qUsers.id.eq(userId))
                .where(builder.and(qUserNotification.id.isNull()))
                .fetch();
    }
}
