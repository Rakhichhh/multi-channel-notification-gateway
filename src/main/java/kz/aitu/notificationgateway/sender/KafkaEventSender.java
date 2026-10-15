package kz.aitu.notificationgateway.sender;

public class KafkaEventSender implements MessageSender {

    private boolean available;

    public KafkaEventSender() {
        this(true);
    }

    public KafkaEventSender(boolean available) {
        this.available = available;
    }

    @Override
    public boolean sendMessage(String recipient, String title, String body) {
        if (!isAvailable()) {
            System.out.println("[Kafka] Unavailable. Event was not published.");
            return false;
        }

        if (recipient == null || recipient.isBlank()
                || body == null || body.isBlank()) {
            System.out.println("[Kafka] Event recipient or body is empty.");
            return false;
        }

        String eventType = (title == null || title.isBlank())
                ? "NotificationEvent"
                : title;

        System.out.println("[Kafka] Published event");
        System.out.println("[Kafka] Topic: notifications");
        System.out.println("[Kafka] Event type: " + eventType);
        System.out.println("[Kafka] Recipient: " + recipient);
        System.out.println("[Kafka] Payload: " + body);
        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "Kafka";
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}