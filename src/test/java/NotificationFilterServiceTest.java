//import org.example.NotificationExample.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import org.mockito.Mock;
//
//import java.time.Instant;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anySet;
//import static org.mockito.Mockito.when;
//
//
//public class NotificationFilterServiceTest {
//    @Mock
//    UserPreferencesProvider preferencesProvider;
//
//    @Mock
//    NotificationHistoryProvider historyProvider;
//
//    NotificationFilterService service;
//
//    @BeforeEach
//    void setUp() {
//        service = new NotificationFilterService(preferencesProvider, historyProvider, 24);
//    }
//
//    @Test
//    void blocksNotificationWhenChannelNotAllowed() {
//        Notification notification =
//                new Notification("1", NotificationType.EMAIL, "user1", "text");
//
//        when(preferencesProvider.getPreferencesForUsers(Set.of("user1")))
//                .thenReturn(Map.of("user1", new UserPreferences(Set.of(NotificationType.SMS), Set.of())));
//
//        when(historyProvider.getSentNotificationsSince(anySet(), any())).thenReturn(List.of());
//
//        List<Notification> result = service.filter("sender1", List.of(notification));
//
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    void blocksDuplicateFromHistory() {
//        Notification notification = new Notification("1", NotificationType.SMS, "user1", "text");
//
//        when(preferencesProvider.getPreferencesForUsers(Set.of("user1")))
//                .thenReturn(Map.of("user1", new UserPreferences(Set.of(NotificationType.SMS), Set.of())));
//
//        when(historyProvider.getSentNotificationsSince(anySet(), any()))
//                .thenReturn(List.of(new SentNotification("1", "user1", Instant.now())));
//
//        List<Notification> result = service.filter("sender1", List.of(notification));
//
//        assertTrue(result.isEmpty());
//    }
//
//    @Test
//    void removesDuplicatesInsideBatch() {
//        Notification n1 = new Notification("1", NotificationType.SMS, "user1", "text");
//        Notification n2 = new Notification("1", NotificationType.SMS, "user1", "text");
//        when(preferencesProvider.getPreferencesForUsers(Set.of("user1")))
//                .thenReturn(Map.of("user1", new UserPreferences(Set.of(NotificationType.SMS), Set.of())));
//        when(historyProvider.getSentNotificationsSince(anySet(), any())).thenReturn(List.of());
//        List<Notification> result = service.filter("sender1", List.of(n1, n2));
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    void allowsNotificationWhenAllChecksPass() {
//        Notification notification = new Notification("1", NotificationType.PUSH, "user1", "text");
//
//        when(preferencesProvider.getPreferencesForUsers(Set.of("user1")))
//                .thenReturn(Map.of("user1", new UserPreferences(Set.of(NotificationType.PUSH), Set.of())));
//
//        when(historyProvider.getSentNotificationsSince(anySet(), any())).thenReturn(List.of());
//        List<Notification> result = service.filter("sender1", List.of(notification));
//        assertEquals(1, result.size());
//    }
//}
