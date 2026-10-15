package kz.aitu.notificationgateway.sender;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SmsMessageSenderTest {

    @Test
    void acceptsMessageWith160Characters() {
        SmsMessageSender sms = new SmsMessageSender(true);
        String message = "x".repeat(160);

        boolean result = sms.sendMessage("+77010000000", null, message);

        assertTrue(result);
    }

    @Test
    void rejectsMessageWith161Characters() {
        SmsMessageSender sms = new SmsMessageSender(true);
        String message = "x".repeat(161);

        boolean result = sms.sendMessage("+77010000000", null, message);

        assertFalse(result);
    }
}