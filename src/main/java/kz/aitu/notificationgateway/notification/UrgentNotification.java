package kz.aitu.notificationgateway.notification;

import kz.aitu.notificationgateway.sender.MessageSender;

public class UrgentNotification extends Notification {

    private final MessageSender fallbackSender;

    public UrgentNotification(
            MessageSender sender,
            MessageSender fallbackSender) {
        super(sender);
        this.fallbackSender = fallbackSender;
    }

    @Override
    public void send(String recipient, String message) {
        String urgentMessage = "[URGENT] " + message;

        if (sender.isAvailable()
                && sender.sendMessage(
                recipient,
                "Urgent Notification",
                urgentMessage)) {
            return;
        }

        System.out.println("[UrgentNotification] Primary sender failed. "
                + "Trying fallback: " + fallbackSender.getChannelName());

        if (fallbackSender.isAvailable()
                && fallbackSender.sendMessage(
                recipient,
                "Urgent Notification",
                urgentMessage)) {
            return;
        }

        System.out.println("[UrgentNotification] No message was sent.");
    }
}