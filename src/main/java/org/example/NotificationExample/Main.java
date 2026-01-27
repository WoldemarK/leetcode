package org.example.NotificationExample;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // === 1. Создаём реализации сервисов ===
        UserSettingsService userSettingsService = new UserSettingsServiceImpl();
        NotificationHistoryService notificationHistoryService = new NotificationHistoryServiceImpl();

        // === 2. Фильтр ===
        NotificationFilter filter = new NotificationFilter(userSettingsService, notificationHistoryService);

        // === 3. Уведомления для теста ===
        List<Notification> notifications = List.of(
                new Notification(1L, NotificationType.EMAIL, "Hello!", 100L),   // дубль → отфильтровано
                new Notification(2L, NotificationType.SMS, "Hi!", 100L),        // SMS запрещён → отфильтровано
                new Notification(3L, NotificationType.EMAIL, "Spam?", 100L),    // отправитель 50 заблокирован → отфильтровано
                new Notification(4L, NotificationType.PUSH, "Welcome!", 200L),  // пройдёт
                new Notification(5L, NotificationType.EMAIL, "News", 200L)      // пройдёт
        );

        // === 4. Фильтрация и вывод ===
        filter.filter(50L, notifications).forEach(n ->
                System.out.printf("ID: %d, Тип: %s, Получатель: %d, Текст: %s%n",
                        n.id(), n.type(), n.recipientId(), n.message())
        );
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
                        String dedupKey = recipientId + "::" + notification.id();

                        // 1. Проверка дубликатов
                        if (recentlySent.contains(dedupKey)) {
                            return false;
                        }

                        // 2. Получение настроек пользователя (с дефолтом на случай отсутствия)
                        UserPreferences prefs = preferencesMap.getOrDefault(recipientId, DEFAULT_PREFERENCES);

                        // 3. Проверка разрешённого типа уведомления
                        if (!prefs.allowedNotificationTypes().contains(notification.type())) {
                            return false;
                        }

                        // 4. Проверка заблокированного отправителя
                        if (prefs.blockedSenders().contains(senderId)) {
                            return false;
                        }
                        return true;
                    })
                    .collect(Collectors.toList());
        }
        private static final UserPreferences DEFAULT_PREFERENCES = UserPreferences.builder()
                .allowedNotificationTypes(Set.of(NotificationType.EMAIL, NotificationType.SMS, NotificationType.PUSH))
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
    @RequiredArgsConstructor
    public static class UserSettingsServiceImpl implements UserSettingsService {

        @Override
        public Map<Long, UserPreferences> getPreferencesForUsers(Set<Long> userIds) {
            final UserPreferences BLOCKED_USER = UserPreferences.builder()
                    .allowedNotificationTypes(Set.of(NotificationType.EMAIL, NotificationType.PUSH))
                    .blockedSenders(Set.of(50L))
                    .build();

             final UserPreferences DEFAULT_USER = UserPreferences.builder()
                    .allowedNotificationTypes(Set.of(NotificationType.EMAIL, NotificationType.SMS, NotificationType.PUSH))
                    .blockedSenders(Set.of())
                    .build();

            return userIds.stream().collect(Collectors.toMap(Function.identity(),
                    id -> id == 100L ? BLOCKED_USER : DEFAULT_USER));
        }
    }

    public interface NotificationHistoryService {
        List<SentNotificationRecord> getSentNotificationsSince(Set<Long> userIds, Instant since);
    }
    public static class NotificationHistoryServiceImpl implements NotificationHistoryService{
        @Override
        public List<SentNotificationRecord> getSentNotificationsSince(Set<Long> userIds, Instant since) {
            return List.of(new SentNotificationRecord(1L,100L,
                    Instant.now().minus(1,ChronoUnit.HOURS)));
        }
    }
}
