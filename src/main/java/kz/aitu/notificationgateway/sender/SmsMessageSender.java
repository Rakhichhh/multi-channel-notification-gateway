package kz.aitu.notificationgateway.sender;

public class SmsMessageSender implements MessageSender {

    private static final int MAX_LENGTH = 160;

    private boolean available;

    public SmsMessageSender() {
        this(true);
    }

    public SmsMessageSender(boolean available) {
        this.available = available;
    }

    @Override
    public boolean sendMessage(String recipient, String title, String body) {
        if (!isAvailable()) {
            System.out.println("[SMS] Unavailable. Message was not sent.");
            return false;
        }

        if (recipient == null || recipient.isBlank()
                || body == null || body.isBlank()) {
            System.out.println("[SMS] Recipient or message is empty.");
            return false;
        }

        String text = (title == null || title.isBlank())
                ? body
                : title + ": " + body;

        if (text.length() > MAX_LENGTH) {
            System.out.println("[SMS] Message is too long: "
                    + text.length() + " characters. Maximum is "
                    + MAX_LENGTH + ".");
            return false;
        }

        System.out.println("[SMS] To: " + recipient);
        System.out.println("[SMS] Message: " + text);
        return true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getChannelName() {
        return "SMS";
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}