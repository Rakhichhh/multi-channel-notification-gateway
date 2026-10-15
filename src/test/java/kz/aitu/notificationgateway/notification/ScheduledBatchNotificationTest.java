package kz.aitu.notificationgateway.notification;

import kz.aitu.notificationgateway.sender.MessageSender;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ScheduledBatchNotificationTest {

    @Test
    void sendsToAllRecipientsAndPrintsSummary() {
        RecordingSender sender = new RecordingSender();
        ScheduledBatchNotification batch =
                new ScheduledBatchNotification(sender);

        batch.addRecipient("user1");
        batch.addRecipient("user2");
        batch.addRecipient("user3");

        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(
                    output, true, StandardCharsets.UTF_8));
            batch.sendToAll("Order is ready");
        } finally {
            System.setOut(originalOut);
        }

        String summary = output.toString(StandardCharsets.UTF_8);
        assertTrue(summary.contains("Total: 3"));
        assertTrue(summary.contains("Successful: 2"));
        assertTrue(summary.contains("Failed: 1"));
    }

    private static class RecordingSender implements MessageSender {

        @Override
        public boolean sendMessage(
                String recipient, String title, String body) {
            return !recipient.equals("user2");
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