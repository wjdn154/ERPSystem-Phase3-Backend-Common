package com.megazone.ERPSystem_phase3_Common.Integrated.service.notification;

import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.Notification;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.UserNotification;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.dto.UserNotificationCreateAndSendDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.dto.UserNotificationSearchDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.dto.UserSubscriptionDTO;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.enums.ModuleType;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.enums.PermissionType;
import com.megazone.ERPSystem_phase3_Common.Integrated.model.notification.enums.Subscription;
import com.megazone.ERPSystem_phase3_Common.Integrated.repository.notification.NotificationRepository;
import com.megazone.ERPSystem_phase3_Common.Integrated.repository.notification.UserNotificationRepository;
import com.megazone.ERPSystem_phase3_Common.common.config.multi_tenant.TenantContext;
import com.megazone.ERPSystem_phase3_Common.user.model.basic_information_management.employee.Users;
import com.megazone.ERPSystem_phase3_Common.user.model.basic_information_management.employee.enums.UserPermission;
import com.megazone.ERPSystem_phase3_Common.user.repository.basic_information_management.Users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserNotificationRepository userNotificationRepository;
    private final Map<String, Subscription> emitters = new ConcurrentHashMap<>();
    private final UsersRepository usersRepository;
    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private static final ScheduledExecutorService sharedExecutor = Executors.newScheduledThreadPool(10);

    // 사용자 구독 관리
    // 사용자 구독 관리
    @Override
    public SseEmitter subscribe(Long employeeId, String tenantId, ModuleType module, PermissionType permission, String uuid) {

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        String key = tenantId + "_" + employeeId + "_" + uuid;
        Subscription subscription = new Subscription(emitter, module, permission);
        emitters.put(key, subscription);

        // 연결 종료 및 타임아웃 시 자원 해제
        emitter.onCompletion(() -> emitters.remove(key));
        emitter.onTimeout(() -> emitters.remove(key));
        emitter.onError(e -> {
            System.out.println(key + ", 에러: " + e.getMessage());
            emitters.remove(key);
        });

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            try {
                emitter.send(SseEmitter.event().data("keep-alive"));
                System.out.println(emitter);
            } catch (Exception e) {
                System.out.println(key + ", Keep-Alive 이벤트 전송 실패: " + e.getMessage());
                emitters.remove(key);
                executor.shutdown();
            }
        }, 0, 15, TimeUnit.SECONDS); // 15초 간격으로 전송

        return emitter;
    }
//// 타임아웃을 무제한으로 설정 (-1L)
//        long timeout = -1L; // 무제한
//        SseEmitter emitter = new SseEmitter(timeout);
//
//        String key = tenantId + "_" + employeeId + "_" + uuid;
//        Subscription subscription = new Subscription(emitter, module, permission);
//
//        synchronized (emitters) {
//            emitters.put(key, subscription);
//        }
//
//// Keep-Alive 작업
//        AtomicReference<ScheduledFuture<?>> futureRef = new AtomicReference<>();
//
//        futureRef.set(sharedExecutor.scheduleAtFixedRate(() -> {
//            try {
//                synchronized (emitters) {
//                    if (!emitters.containsKey(key)) {
//                        logger.warn("{} emitter가 이미 제거되었습니다. 전송을 중단합니다.", key);
//                        return;
//                    }
//                    emitter.send(SseEmitter.event().data("keep-alive"));
//                }
//            } catch (IOException e) {
//                logger.warn("{} Keep-Alive 전송 실패: 클라이언트 연결 종료 감지. {}", key, e.getMessage());
//                synchronized (emitters) {
//                    emitters.remove(key);
//                }
//                emitter.complete();
//                cancelFuture(futureRef);
//            } catch (Exception e) {
//                logger.error("{} 예상치 못한 오류 발생: {}", key, e.getMessage());
//                synchronized (emitters) {
//                    emitters.remove(key);
//                }
//                emitter.completeWithError(e);
//                cancelFuture(futureRef);
//            }
//        }, 0, 15, TimeUnit.SECONDS)); // 15초 간격 Keep-Alive
//
//// emitter 종료 시 작업
//        emitter.onCompletion(() -> {
//            synchronized (emitters) {
//                logger.info("{} 연결 정상 종료", key);
//                emitters.remove(key);
//            }
//            cancelFuture(futureRef);
//        });
//
//        emitter.onError(e -> {
//            synchronized (emitters) {
//                logger.error("{} 연결 오류 발생: {}", key, e.getMessage());
//                emitters.remove(key);
//            }
//            cancelFuture(futureRef);
//        });
//
//        return emitter;
//
//    }
//    private void cancelFuture(AtomicReference<ScheduledFuture<?>> futureRef) {
//        ScheduledFuture<?> future = futureRef.get();
//        if (future != null) {
//            future.cancel(true); // 작업 중단
//        }
//    }
    // 트랜잭션 내에서 사용자 정보 조회
    @Override
    @Transactional(readOnly = true)
    public UserSubscriptionDTO getUserSubscriptionInfo(Long employeeId, boolean isAdmin) {
        Users users = usersRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        String departmentName = users.getEmployee().getDepartment().getDepartmentName();

        // 모듈 및 권한 설정 로직
        ModuleType module = isAdmin ? ModuleType.ALL : switch (departmentName) {
            case "인사부" -> ModuleType.HR;
            case "재무부" -> ModuleType.FINANCE;
            case "생산부" -> ModuleType.PRODUCTION;
            case "물류부" -> ModuleType.LOGISTICS;
            default -> null;
        };

        PermissionType permission = isAdmin ? PermissionType.ALL : (users.getPermission().getAdminPermission() == UserPermission.ADMIN ? PermissionType.ADMIN : PermissionType.USER);

        return new UserSubscriptionDTO(module, permission);
    }

    @Override
    public void removeEmitter(Long employeeId, String uuid) {
        String key = TenantContext.getCurrentTenant() + "_" + employeeId + "_" + uuid;
        Subscription subscription = emitters.get(key);
        if (subscription != null) {
            emitters.remove(key);
            subscription.getEmitter().complete();
        }
    }

    @Override
    @Transactional
    public List<UserNotificationSearchDTO> createAndSearch(Long employeeId, ModuleType module, PermissionType permission) {
        // 사용자 조회
        Long userId = usersRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("ID: " + employeeId + "에 해당하는 사용자를 찾을 수 없습니다.")).getId();

        // 기존 알림 ID 목록 조회
        List<Long> existingNotificationIds = userNotificationRepository.findNotificationIdsByUserId(userId);

        // 중복 알림 필터링 및 새 알림 생성
        List<UserNotification> newNotifications = notificationRepository.fetchNotification(userId, module, permission).stream()
                .filter(notification -> !existingNotificationIds.contains(notification.getNotification().getId()))
                .map(notification -> UserNotification.builder()
                        .userId(userId)
                        .notification(notification.getNotification())
                        .module(notification.getModule())
                        .permission(notification.getPermission())
                        .type(notification.getType())
                        .content(notification.getContent())
                        .createAt(notification.getCreateAt())
                        .readStatus(false)
                        .build())
                .collect(Collectors.toList());

        // 새 알림 저장
        userNotificationRepository.saveAll(newNotifications);

        // 저장된 알림 반환
        return userNotificationRepository.findByUserIdOrderByCreateAtDesc(userId).stream()
                .map(userNotification -> UserNotificationSearchDTO.builder()
                        .userId(userNotification.getUserId())
                        .notification(userNotification.getNotification())
                        .module(userNotification.getModule().getKoreanName())
                        .permission(userNotification.getPermission().getKoreanName())
                        .type(userNotification.getType().getKoreanName())
                        .content(userNotification.getContent())
                        .createAt(userNotification.getCreateAt())
                        .readAt(userNotification.getReadAt())
                        .readStatus(userNotification.isReadStatus())
                        .build())
                .toList();
    }

    @Override
    public Long markAsRead(Long employeeId, Long notificationId) {

        Long userId = usersRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException("ID: " + employeeId + "에 해당하는 사용자를 찾을 수 없습니다.")).getId();
        UserNotification userNotification = userNotificationRepository.findByUserIdAndNotificationId(userId, notificationId).orElseThrow(() -> new IllegalArgumentException("ID: " + notificationId + "에 해당하는 알림을 찾을 수 없습니다."));

        userNotification.setReadAt(LocalDateTime.now());
        userNotification.setReadStatus(true);

        userNotificationRepository.save(userNotification);

        return userNotification.getId();
    }


    // 알림 생성 및 전송 통합
    @Override
    @Transactional
    public Notification createAndSendNotification(UserNotificationCreateAndSendDTO requestData) {
        // 알림 생성
        Notification notification= Notification.builder()
                .module(requestData.getModuleType())
                .permission(requestData.getPermissionType())
                .content(requestData.getNotificationDescription())
                .type(requestData.getNotificationType())
                .createAt(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);

        // 모듈과 권한에 맞는 사용자에게 알림 전송
        sendNotification(notification, TenantContext.getCurrentTenant());

        return notification;
    }

    // 모듈 및 권한에 따라 알림 전송
    @Async
    @Override
    public void sendNotification(Notification notification, String tenantId) {

        SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext()); // 비동기 처리 시 SecurityContextHolder 초기화
        System.out.println("emitters = " + emitters);

        emitters.forEach((key, subscription) -> {

            // 모든 구독자에게 전송할지 여부 확인
            boolean isForAllModules = notification.getModule() == ModuleType.ALL || subscription.getModule() == ModuleType.ALL;
            boolean isForAllPermissions = notification.getPermission() == PermissionType.ALL || subscription.getPermission() == PermissionType.ALL;

            // 테넌트 조건 및 모듈/권한 조건에 맞는 구독자에게만 전송
            if (key.startsWith(tenantId) &&
                    (isForAllModules || subscription.getModule() == notification.getModule()) &&
                    (isForAllPermissions || subscription.getPermission() == notification.getPermission())) {
                try {
                    System.out.println(subscription.getEmitter());
                    subscription.getEmitter().send(SseEmitter.event().name("notification").data(notification.getContent()));
                } catch (Exception e) {
                    subscription.getEmitter().completeWithError(e);
                    emitters.remove(key);
                }
            }
        });
    }
}