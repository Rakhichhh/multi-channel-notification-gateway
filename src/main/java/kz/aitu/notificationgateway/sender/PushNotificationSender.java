package kz.aitu.notificationgateway.sender;

public class PushNotificationSender implements MessageSender {

    private boolean available;

    public PushNotificationSender() {
        this(true);
    }

    public PushNotificationSender(boolean available) {
        this.available = available;
    }

    @Override
    public boolean sendMessage(String recipient, String title, String body) {
        if (!isAvailable()) {
            System.out.println("[Push] Unavailable. Notification was not sent.");
            return false;
        }

        if (recipient == null || recipient.isBlank()
                || body == null || body.isBlank()) {
            System.out.println("[Push] Recipient or message is empty.");
            return false;
        }

        String pushTitle = (title == null || title.isBlank())
                ? "Notification"
                : title;

        System.out.println("[Push] Device: " + recipient);
        System.out.println("[Push] Title: " + pushTitle);
        System.out.println("[Push] Notification: " + body);
        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "Push";
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}