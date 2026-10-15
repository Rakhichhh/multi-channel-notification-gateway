# Multi-Channel Notification Gateway
**Student:** Myrzabekov Rakhat  
**Group:** SE-2518
A Java 17 project that demonstrates the Bridge design pattern.

The system separates notification types from message delivery providers.
Notifications use the `MessageSender` interface, so the sender can be
changed at runtime.

## Implemented features

### Notification types

- `UrgentNotification` adds `[URGENT]` and supports a fallback sender.
- `EncryptedNotification` encodes the message with Base64.
- `ScheduledBatchNotification` sends a message to multiple recipients
  and prints a delivery summary.
- `MarketingNotification` adds a `[MARKETING]` label.

### Message senders

- `TelegramMessageSender` simulates formatted messages.
- `EmailMessageSender` simulates an email subject and signature.
- `SmsMessageSender` limits the complete SMS text to 160 characters.
- `KafkaEventSender` simulates publishing an event.
- `PushNotificationSender` simulates a push notification.

All providers are simulated. No external services are used.

## Running the project

Run `Main` from the IDE to see the required scenarios.

Run the JUnit 5 tests with Maven:

```bash
mvn test
```

## Bridge Pattern

### 1. What are the two independent dimensions?

The two dimensions are **notification types** and **delivery providers**.
Notification types define what the notification does. Providers define
where and how the message is delivered.

### 2. Where exactly is the Bridge?

`Notification` has a `MessageSender` field. The notification calls the
sender through the `MessageSender` interface. This connection is the
Bridge between the abstraction and its implementation.

### 3. Why is inheritance problematic here?

If every notification type had a separate subclass for every provider,
the number of classes would grow as notification types and providers are
added. For example, urgent Telegram, urgent Email, and urgent SMS would
all need separate classes.

### 4. What happens when a new provider is added?

A new provider implements `MessageSender`. Existing notification classes
can use it without being modified.

### 5. What happens when a new notification type is added?

A new class extends `Notification` and uses an existing `MessageSender`.
Existing providers do not need to be modified.

### 6. Why is this Bridge rather than Adapter?

Bridge is used here to design two independent dimensions from the start:
notification types and delivery providers. An Adapter normally makes an
existing incompatible interface work with another interface.

## UML Class Diagram

See the class diagram in `uml/bridge.puml`.