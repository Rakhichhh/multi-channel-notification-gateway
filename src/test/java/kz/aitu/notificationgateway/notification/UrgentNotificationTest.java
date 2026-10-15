package kz.aitu.notificationgateway.notification;

import kz.aitu.notificationgateway.sender.MessageSender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrgentNotificationTest {

    @Test
    void usesFallbackWhenPrimarySenderIsUnavailable() {
        RecordingSender primary = new RecordingSender(false);
        RecordingSender fallback = new RecordingSender(true);
        UrgentNotification notification =
                new UrgentNotification(primary, fallback);

        notification.send("user1", "Emergency!");

        assertEquals(0, primary.sentCount);
        assertEquals(1, fallback.sentCount);
        assertEquals("[URGENT] Emergency!", fallback.sentBody);
    }

    @Test
    void switchesSenderAtRuntime() {
        RecordingSender telegram = new RecordingSender(true);
        RecordingSender email = new RecordingSender(true);
        UrgentNotification notification =
                new UrgentNotification(telegram, new RecordingSender(true));

        notification.send("user1", "First message");
        notification.setSender(email);
        notification.send("user1", "Second message");

        assertEquals(1, telegram.sentCount);
        assertEquals(1, email.sentCount);
        assertEquals("[URGENT] Second message", email.sentBody);
    }

    private static class RecordingSender implements MessageSender {
        private final boolean available;
        private int sentCount;
        private String sentBody;

        private RecordingSender(boolean available) {
            this.available = available;
        }

        @Override
        public boolean sendMessage(
                String recipient, String title, String body) {
            sentCount++;
            sentBody = body;
            return true;
        }

        @Override
        public boolean isAvailable() {
            return available;
        }

        @Override
        public String getChannelName() {
            return "Recording";
        }
    }
}