package kz.aitu.notificationgateway.notification;

import kz.aitu.notificationgateway.sender.MessageSender;

public class MarketingNotification extends Notification {

    public MarketingNotification(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(String recipient, String message) {
        sender.sendMessage(
                recipient,
                "Special Offer",
                "[MARKETING] " + message
        );
    }
}