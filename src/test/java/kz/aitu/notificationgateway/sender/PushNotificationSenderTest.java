package kz.aitu.notificationgateway.sender;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PushNotificationSenderTest {

    @Test
    void sendsPushWhenProviderIsAvailable() {
        PushNotificationSender push =
                new PushNotificationSender(true);

        boolean result = push.sendMessage(
                "device-42",
                "Special Offer",
                "Get 20% off today!"
        );

        assertTrue(result);
    }

    @Test
    void doesNotSendPushWhenProviderIsUnavailable() {
        PushNotificationSender push =
                new PushNotificationSender(false);

        boolean result = push.sendMessage(
                "device-42",
                "Special Offer",
                "Get 20% off today!"
        );

        assertFalse(result);
    }
}