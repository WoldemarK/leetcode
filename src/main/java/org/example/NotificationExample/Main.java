package org.example.NotificationExample;

import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        NotificationFilterService notificationFilterService = new NotificationFilterService(
                recipientIds -> {
                    Map<String, UserPreferences> prefs = new HashMap<>();
                    for (String userId : recipientIds) {
                        prefs.put(userId, new UserPreferences(
                                Set.of(NotificationType.SMS, NotificationType.PUSH),
                                Set.of("4")
                        ));
                    }
                    return prefs;
                },
                (recipientIds, since) ->
                        List.of(new HistorySentNotification("1", "user3", Instant.now())));

        List<Notification> notifications = List.of
                (
                        new Notification("1", NotificationType.EMAIL, "user1", "Welcome to our service!"),
                        new Notification("2", NotificationType.EMAIL, "user1", "Special offer inside!"),
                        new Notification("3", NotificationType.SMS, "user1", "Your account updated"),
                        new Notification("4", NotificationType.SMS, "user2", "Weekly promotion"),
                        new Notification("5", NotificationType.EMAIL, "user1", "Welcome to our service!"),
                        new Notification("6", NotificationType.EMAIL, "user3", "Weekly digest"),
                        new Notification("7", NotificationType.PUSH, "user3", "New message received")
                );


        List<Notification> filtered = notificationFilterService.filter("1", notifications);


        filtered.forEach(n -> System.out.printf("  ✓ [%s] to %s via %s: \"%s\"%n",
                n.notificationId(), n.recipientId(), n.type(), n.messageText()));

        System.out.println("Notifications BLOCKED:");
        notifications.stream()
                .filter(n -> !filtered.contains(n))
                .forEach(n -> System.out.printf("  ✗ [%s] to %s via %s: \"%s\"%n",
                        n.notificationId(), n.recipientId(), n.type(), n.messageText()));
    }
}

interface UserPreferencesProvider {
    Map<String, UserPreferences> getPreferencesForUsers(Set<String> recipientIds);
}

interface NotificationHistoryProvider {
    List<HistorySentNotification> getSentNotificationsSince(Set<String> recipientIds, Instant since);
}

enum NotificationType {
    EMAIL, SMS, PUSH
}

record Notification(String notificationId, NotificationType type, String recipientId, String messageText) {
}

record UserPreferences(
        Set<NotificationType> allowedChannels,
        Set<String> blockedSenders
) {
    public boolean isChannelAllowed(NotificationType type) {
        return allowedChannels.contains(type);
    }

    public boolean isSenderBlocked(String senderId) {
        return blockedSenders.contains(senderId);
    }
}

record HistorySentNotification(String notificationId, String recipientId, Instant sentAt) {
}

@RequiredArgsConstructor
class NotificationFilterService {
    private final UserPreferencesProvider preferencesProvider;
    private final NotificationHistoryProvider historyProvider;


    public List<Notification> filter(String senderId, List<Notification> notifications) {
        Objects.requireNonNull(senderId, "Sender ID must not be null");

        if (notifications == null || notifications.isEmpty()) {
            return List.of();
        }

        //Собираем всех получателей
        Set<String> recipientIds = notifications.stream().map(Notification::recipientId).collect(Collectors.toSet());
        //Загружаем настройки пользователей
        Map<String, UserPreferences> preferencesMap = preferencesProvider.getPreferencesForUsers(recipientIds);
        // Определяем "последние 24 часа"
        Instant cutoffTime = Instant.now().minus(24, ChronoUnit.HOURS);
        //Загружаем уже отправленные письма
        Set<String> recentDuplicates = extractRecentDuplicates(recipientIds, cutoffTime);
        Set<String> batchDuplicates = new HashSet<>();
        //
        List<Notification> result = new ArrayList<>();

        for (Notification notification : notifications) {
            //Строим ключ письма
            String key = notification.recipientId() + "::" + notification.notificationId();
            //Проверяем дубли ВНУТРИ текущего списка
            if (batchDuplicates.contains(key)) {
                continue;
            }
            //Проверяем можно ли доставить
            if (shouldDeliver(new FilterNotificationRecord(notification, senderId, preferencesMap, recentDuplicates))) {
                result.add(notification);
                batchDuplicates.add(key);
            }
        }

        return result;
    }

    // Берём старую историю писем
    private Set<String> extractRecentDuplicates(Set<String> recipientIds, Instant at) {
        return historyProvider.getSentNotificationsSince(recipientIds, at)
                .stream()
                .map(sent -> sent.recipientId() + "::" + sent.notificationId())
                .collect(Collectors.toSet());
    }

    //решение судьбы письма
    private boolean shouldDeliver(FilterNotificationRecord record) {
        //Получаем настройки человека
        UserPreferences preferences =
                record.preferencesMap().get(record.notification().recipientId());
        //Проверяем разрешения Письмо можно отправить, если правил нет
        boolean allowedByPreferences = preferences == null
                //или канал разрешён И отправитель не заблокирован
                || (preferences.isChannelAllowed(record.notification().type())
                && !preferences.isSenderBlocked(record.senderId()));
        //Если нельзя → сразу отказ Человек не хочет такие письма
        if (!allowedByPreferences) {
            return false;
        }
         //Проверяем историю Не отправляли ли уже такое письмо недавн
        return !record.recentDuplicates()
                .contains("%s::%s".formatted(record.notification().recipientId(), record.notification().notificationId()));
    }
}

record FilterNotificationRecord(Notification notification,
                                String senderId,
                                Map<String, UserPreferences> preferencesMap,
                                Set<String> recentDuplicates) {

}