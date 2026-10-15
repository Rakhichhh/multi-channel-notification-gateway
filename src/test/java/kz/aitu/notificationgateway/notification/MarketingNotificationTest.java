package kz.aitu.notificationgateway.notification;

import kz.aitu.notificationgateway.sender.PushNotificationSender;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MarketingNotificationTest {

    @Test
    void addsMarketingLabelAndSendsThroughPush() {
        MarketingNotification notification =
                new MarketingNotification(new PushNotificationSender());

        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(
                    output, true, StandardCharsets.UTF_8));
            notification.send("device-42", "Get 20% off today!");
        } finally {
            System.setOut(originalOut);
        }

        String result = output.toString(StandardCharsets.UTF_8);
        assertTrue(result.contains("[MARKETING] Get 20% off today!"));
    }
}