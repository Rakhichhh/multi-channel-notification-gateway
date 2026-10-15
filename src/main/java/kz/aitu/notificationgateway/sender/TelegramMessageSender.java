package kz.aitu.notificationgateway.sender;

public class TelegramMessageSender implements MessageSender {

    private boolean available;

    public TelegramMessageSender() {
        this(true);
    }

    public TelegramMessageSender(boolean available) {
        this.available = available;
    }

    @Override
    public boolean sendMessage(String recipient, String title, String body) {
        if (!isAvailable()) {
            System.out.println("[Telegram] Unavailable. Message was not sent.");
            return false;
        }

        if (recipient == null || recipient.isBlank()
                || body == null || body.isBlank()) {
            System.out.println("[Telegram] Recipient or message is empty.");
            return false;
        }

        System.out.println("[Telegram] To: " + recipient);

        if (title != null && !title.isBlank()) {
            System.out.println("[Telegram] Title: " + title);
        }

        System.out.println("[Telegram] Formatted message: <b>" + body + "</b>");
        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "Telegram";
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}