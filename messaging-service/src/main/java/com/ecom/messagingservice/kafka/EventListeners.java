package com.ecom.messagingservice.kafka;

import com.ecom.messagingservice.domain.EventLog;
import com.ecom.messagingservice.repo.EventLogRepository;
import com.ecom.shared.events.NotificationEvent;
import com.ecom.shared.events.OrderCreatedEvent;
import com.ecom.shared.events.PaymentCompletedEvent;
import com.ecom.shared.outbox.OutboxTopics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class EventListeners {

  private final EventLogRepository repo;
  private final KafkaTemplate<String, String> kafka;

  public EventListeners(EventLogRepository repo, KafkaTemplate<String, String> kafka) {
    this.repo = repo;
    this.kafka = kafka;
  }

  @KafkaListener(topics = "order.created", groupId = "messaging-service")
  public void onOrderCreated(String payload) {
    save("order.created", payload);
    try {
      var mapper = new com.fasterxml.jackson.databind.ObjectMapper();
      OrderCreatedEvent event = mapper.readValue(payload, OrderCreatedEvent.class);
      NotificationEvent notif = new NotificationEvent(UUID.randomUUID().toString(), Instant.now(), "EMAIL",
          event.userId() + "@example.com", "Order created", "OrderId=" + event.orderId());
      kafka.send(OutboxTopics.NOTIFICATION_EVENT, notif.eventId(), mapper.writeValueAsString(notif));
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  @KafkaListener(topics = "payment.completed", groupId = "messaging-service")
  public void onPaymentCompleted(String payload) {
    save("payment.completed", payload);
    try {
      var mapper = new com.fasterxml.jackson.databind.ObjectMapper();
      PaymentCompletedEvent event = mapper.readValue(payload, PaymentCompletedEvent.class);
      NotificationEvent notif = new NotificationEvent(UUID.randomUUID().toString(), Instant.now(), "EMAIL",
          "order-" + event.orderId() + "@example.com", "Payment " + event.status(), event.providerRef());
      kafka.send(OutboxTopics.NOTIFICATION_EVENT, notif.eventId(), mapper.writeValueAsString(notif));
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  private void save(String topic, String payload) {
    EventLog l = new EventLog();
    l.setTopic(topic);
    l.setPayload(payload);
    repo.save(l);
  }
}
