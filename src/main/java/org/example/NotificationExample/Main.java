package org.example.NotificationExample;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // === 1. Создаём моки сервисов ===

        UserSettingsService userSettingsService = userIds -> {
            Map<Long, UserPreferences> prefs = new HashMap<>();
            for (Long id : userIds) {
                if (id == 100L) {
                    // Пользователь 100: разрешены только EMAIL и PUSH, заблокирован отправитель 50
                    prefs.put(id, UserPreferences.builder()
                            .allowedNotificationTypes(Set.of(NotificationType.EMAIL, NotificationType.PUSH))
                            .blockedSenders(Set.of(50L))
                            .build());
                } else {
                    // Остальные: всё разрешено, никто не заблокирован
                    prefs.put(id, UserPreferences.builder()
                            .allowedNotificationTypes(Set.of(NotificationType.EMAIL, NotificationType.SMS, NotificationType.PUSH))
                            .blockedSenders(Set.of())
                            .build());
                }
            }
            return prefs;
        };

        NotificationHistoryService notificationHistoryService = (userIds, since) -> {
            // Имитируем, что пользователю 100 уже отправляли уведомление ID=1 за последние 24 часа
            Instant now = Instant.now();
            return List.of(
                    new SentNotificationRecord(1L, 100L, now.minus(1, ChronoUnit.HOURS))
            );
        };

        // === 2. Создаём фильтр ===
        NotificationFilter filter = new NotificationFilter(userSettingsService, notificationHistoryService);

        // === 3. Подготавливаем входные уведомления ===
        List<Notification> notifications = Arrays.asList(
                // Уведомление 1: уже отправлялось пользователю 100 → должно быть отфильтровано (дубль)
                new Notification(1L, NotificationType.EMAIL, "Hello!", 100L),

                // Уведомление 2: SMS для пользователя 100 → запрещён канал → отфильтровано
                new Notification(2L, NotificationType.SMS, "Hi!", 100L),

                // Уведомление 3: EMAIL от отправителя 50 → заблокирован → отфильтровано
                new Notification(3L, NotificationType.EMAIL, "Spam?", 100L),

                // Уведомление 4: PUSH для пользователя 200 → всё разрешено → пройдёт
                new Notification(4L, NotificationType.PUSH, "Welcome!", 200L),

                // Уведомление 5: EMAIL для пользователя 200 → пройдёт
                new Notification(5L, NotificationType.EMAIL, "News", 200L)
        );

        // === 4. Запускаем фильтрацию (отправитель = 50) ===
        Long senderId = 50L;
        List<Notification> result = filter.filter(senderId, notifications);

        // === 5. Выводим результат ===
        System.out.println("Отфильтрованные уведомления (готовые к отправке):");
        for (Notification n : result) {
            System.out.println("ID: " + n.id() +
                    ", Тип: " + n.type() +
                    ", Получатель: " + n.recipientId() +
                    ", Текст: " + n.message());
        }
    }

    @RequiredArgsConstructor
    static class NotificationFilter {

        private final UserSettingsService userSettingsService;
        private final NotificationHistoryService notificationHistoryService;
        private final Clock clock;

        public NotificationFilter(UserSettingsService userSettingsService,
                                  NotificationHistoryService notificationHistoryService) {
            this(userSettingsService, notificationHistoryService, Clock.systemUTC());
        }

        public List<Notification> filter(Long senderId, List<Notification> notifications) {
            if (notifications == null || notifications.isEmpty()) {
                return Collections.emptyList();
            }

            Set<Long> recipientIds = notifications.stream()
                    .map(Notification::recipientId)
                    .collect(Collectors.toSet());

            Map<Long, UserPreferences> preferencesMap = userSettingsService.getPreferencesForUsers(recipientIds);

            Instant cutoff = Instant.now(clock).minus(24, ChronoUnit.HOURS);
            List<SentNotificationRecord> recentHistory = notificationHistoryService.getSentNotificationsSince(recipientIds, cutoff);

            Set<String> recentlySent = recentHistory.stream()
                    .map(sent -> sent.recipientId() + "::" + sent.notificationId())
                    .collect(Collectors.toSet());

            return notifications.stream()
                    .filter(notification -> {
                        Long recipientId = notification.recipientId();

                        // 1. Проверка дубликата
                        String dedupKey = recipientId + "::" + notification.id();
                        if (recentlySent.contains(dedupKey)) {
                            return false;
                        }

                        // 2. Получение предпочтений
                        UserPreferences prefs = preferencesMap.getOrDefault(recipientId, DEFAULT_PREFERENCES);

                        // 3. Разрешён ли канал
                        if (!prefs.allowedNotificationTypes().contains(notification.type())) {
                            return false;
                        }

                        // 4. Заблокирован ли отправитель
                        if (prefs.blockedSenders().contains(senderId)) {
                            return false;
                        }

                        return true;
                    })
                    .collect(Collectors.toList());
        }

        private static final UserPreferences DEFAULT_PREFERENCES = UserPreferences.builder()
                .allowedNotificationTypes(Set.of(NotificationType.PUSH, NotificationType.EMAIL, NotificationType.SMS))
                .blockedSenders(Set.of())
                .build();
    }


    @Builder
    public record UserPreferences(Set<Long> blockedSenders, Set<NotificationType> allowedNotificationTypes) {
    }

    public record Notification(Long id, NotificationType type, String message, Long recipientId) {
    }


    public record SentNotificationRecord(Long notificationId, Long recipientId, Instant sentAt) {
    }

    public enum NotificationType {
        EMAIL, SMS, PUSH
    }

    public interface UserSettingsService {
        Map<Long, UserPreferences> getPreferencesForUsers(Set<Long> userIds);
    }

    public interface NotificationHistoryService {
        List<SentNotificationRecord> getSentNotificationsSince(Set<Long> userIds, Instant since);
    }
}
