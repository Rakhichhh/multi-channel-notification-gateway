package kz.aitu.notificationgateway.sender;

public class EmailMessageSender implements MessageSender {

    private boolean available;

    public EmailMessageSender() {
        this(true);
    }

    public EmailMessageSender(boolean available) {
        this.available = available;
    }

    @Override
    public boolean sendMessage(String recipient, String title, String body) {
        if (!isAvailable()) {
            System.out.println("[Email] Unavailable. Message was not sent.");
            return false;
        }

        if (recipient == null || recipient.isBlank()
                || body == null || body.isBlank()) {
            System.out.println("[Email] Recipient or message is empty.");
            return false;
        }

        String emailTitle =
                (title == null || title.isBlank()) ? "(No title)" : title;

        System.out.println("[Email] To: " + recipient);
        System.out.println("[Email] Subject: " + emailTitle);
        System.out.println("[Email] Body: " + body);
        System.out.println("[Email] Signature: Notification Gateway");
        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "Email";
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}