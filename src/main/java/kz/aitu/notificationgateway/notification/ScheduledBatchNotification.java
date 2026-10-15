package kz.aitu.notificationgateway.notification;

import java.util.ArrayList;
import java.util.List;
import kz.aitu.notificationgateway.sender.MessageSender;

public class ScheduledBatchNotification extends Notification {

    private final List<String> recipients = new ArrayList<>();

    public ScheduledBatchNotification(MessageSender sender) {
        super(sender);
    }

    public void addRecipient(String recipient) {
        if (recipient == null || recipient.isBlank()) {
            throw new IllegalArgumentException(
                    "Recipient cannot be empty.");
        }
        recipients.add(recipient);
    }

    @Override
    public void send(String recipient, String message) {
        sendToAll(message);
    }

    public void sendToAll(String message) {
        int successful = 0;

        for (String recipient : recipients) {
            boolean sent = sender.isAvailable()
                    && sender.sendMessage(
                    recipient,
                    "Batch Notification",
                    message
            );

            if (sent) {
                successful++;
            }
        }

        int total = recipients.size();
        int failed = total - successful;

        System.out.println("Total: " + total);
        System.out.println("Successful: " + successful);
        System.out.println("Failed: " + failed);
    }
}