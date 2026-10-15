package kz.aitu.notificationgateway.sender;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TelegramMessageSenderTest {

    @Test
    void sendsMessageWhenTelegramIsAvailable() {
        TelegramMessageSender telegram = new TelegramMessageSender(true);

        boolean result = telegram.sendMessage(
                "user123",
                "Test",
                "Hello"
        );

        assertTrue(result);
    }

    @Test
    void doesNotSendMessageWhenTelegramIsUnavailable() {
        TelegramMessageSender telegram = new TelegramMessageSender(false);

        boolean result = telegram.sendMessage(
                "user123",
                "Test",
                "Hello"
        );

        assertFalse(result);
    }
}