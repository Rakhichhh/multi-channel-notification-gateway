package kz.aitu.notificationgateway.app;

import kz.aitu.notificationgateway.notification.EncryptedNotification;
import kz.aitu.notificationgateway.notification.MarketingNotification;
import kz.aitu.notificationgateway.notification.Notification;
import kz.aitu.notificationgateway.notification.ScheduledBatchNotification;
import kz.aitu.notificationgateway.notification.UrgentNotification;
import kz.aitu.notificationgateway.sender.EmailMessageSender;
import kz.aitu.notificationgateway.sender.KafkaEventSender;
import kz.aitu.notificationgateway.sender.PushNotificationSender;
import kz.aitu.notificationgateway.sender.SmsMessageSender;
import kz.aitu.notificationgateway.sender.TelegramMessageSender;

public class Main {

    public static void main(String[] args) {
        TelegramMessageSender telegram = new TelegramMessageSender();
        EmailMessageSender email = new EmailMessageSender();
        SmsMessageSender sms = new SmsMessageSender();
        KafkaEventSender kafka = new KafkaEventSender();
        PushNotificationSender push = new PushNotificationSender();

        System.out.println("1. UrgentNotification + Telegram");
        Notification switchable =
                new UrgentNotification(telegram, sms);
        switchable.send("user123", "Server is down!");

        System.out.println("\n2. EncryptedNotification + Email");
        Notification encrypted = new EncryptedNotification(email);
        encrypted.send("user@example.com", "Your code is 1234");

        System.out.println("\n3. ScheduledBatchNotification + SMS");
        ScheduledBatchNotification batch =
                new ScheduledBatchNotification(sms);
        batch.addRecipient("+77010000001");
        batch.addRecipient("+77010000002");
        batch.addRecipient("+77010000003");
        batch.sendToAll("Your order is ready.");

        System.out.println("\n4. Runtime switching: Telegram -> Email");
        switchable.setSender(email);
        switchable.send("user@example.com", "Provider changed at runtime.");

        System.out.println("\n5. Telegram unavailable -> fallback to SMS");
        TelegramMessageSender unavailableTelegram =
                new TelegramMessageSender(false);
        Notification urgentWithFallback =
                new UrgentNotification(unavailableTelegram, sms);
        urgentWithFallback.send("+77010000004", "Emergency alert!");

        System.out.println("\n6. MarketingNotification + PushNotificationSender");
        Notification marketing = new MarketingNotification(push);
        marketing.send("device-42", "Get 20% off today!");

        System.out.println("\nExtra: Kafka event sender");
        kafka.sendMessage("user123", "UserRegistered", "New user registered.");
    }
}