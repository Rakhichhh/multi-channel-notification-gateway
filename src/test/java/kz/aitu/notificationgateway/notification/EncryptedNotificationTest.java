package kz.aitu.notificationgateway.notification;

import kz.aitu.notificationgateway.sender.MessageSender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EncryptedNotificationTest {

    @Test
    void encodesMessageBeforeSending() {
        RecordingSender sender = new RecordingSender();
        EncryptedNotification notification =
                new EncryptedNotification(sender);

        notification.send("user@example.com", "Hello");

        assertNotEquals("Hello", sender.sentBody);
        assertEquals("SGVsbG8=", sender.sentBody);
    }

    private static class RecordingSender implements MessageSender {
        private String sentBody;

        @Override
        public boolean sendMessage(
                String recipient, String title, String body) {
            sentBody = body;
            return true;
        }

        @Override
        public boolean isAvailable() {
            return true;
        }

        @Override
        public String getChannelName() {
            return "Recording";
        }
    }
}