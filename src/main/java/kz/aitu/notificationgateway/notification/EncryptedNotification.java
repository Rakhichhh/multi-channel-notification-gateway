package kz.aitu.notificationgateway.notification;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import kz.aitu.notificationgateway.sender.MessageSender;

public class EncryptedNotification extends Notification {

    public EncryptedNotification(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(String recipient, String message) {
        String encodedMessage = Base64.getEncoder()
                .encodeToString(message.getBytes(StandardCharsets.UTF_8));

        sender.sendMessage(
                recipient,
                "Encrypted Notification",
                encodedMessage
        );
    }
}