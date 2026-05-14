package com.ecom.paymentservice.kafka;

import com.ecom.paymentservice.domain.Payment;
import com.ecom.paymentservice.outbox.OutboxService;
import com.ecom.paymentservice.repo.PaymentRepository;
import com.ecom.shared.events.OrderCreatedEvent;
import com.ecom.shared.events.PaymentCompletedEvent;
import com.ecom.shared.outbox.OutboxTopics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
public class OrderCreatedConsumer {

  private final PaymentRepository repo;
  private final OutboxService outbox;

  public OrderCreatedConsumer(PaymentRepository repo, OutboxService outbox) {
    this.repo = repo;
    this.outbox = outbox;
  }

  @KafkaListener(topics = "order.created", groupId = "payment-service")
  @Transactional
  public void onOrderCreated(String payload) {
    try {
      var mapper = new com.fasterxml.jackson.databind.ObjectMapper();
      OrderCreatedEvent event = mapper.readValue(payload, OrderCreatedEvent.class);

      Payment p = repo.findByOrderId(event.orderId())
          .orElseGet(() -> repo.save(Payment.initiated(event.orderId(), event.amount(), event.currency())));

      p.setStatus("SUCCESS");
      p.setProviderRef("MOCK-" + java.util.UUID.randomUUID());
      p.setUpdatedAt(Instant.now());
      repo.save(p);

      PaymentCompletedEvent out = new PaymentCompletedEvent(null, Instant.now(), p.getId(), p.getOrderId(), p.getStatus(), p.getProviderRef());
      outbox.saveEvent("Payment", p.getId().toString(), "PaymentCompleted", OutboxTopics.PAYMENT_COMPLETED,
          p.getOrderId().toString(), out);

    } catch (Exception e) {
      throw new IllegalStateException("Failed to process order.created", e);
    }
  }
}
