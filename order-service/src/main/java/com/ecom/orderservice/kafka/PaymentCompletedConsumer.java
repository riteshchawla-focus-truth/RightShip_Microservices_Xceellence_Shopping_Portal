package com.ecom.orderservice.kafka;

import com.ecom.orderservice.repo.OrderRepository;
import com.ecom.shared.events.PaymentCompletedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PaymentCompletedConsumer {

  private final OrderRepository repo;

  public PaymentCompletedConsumer(OrderRepository repo) { this.repo = repo; }

  @KafkaListener(topics = "payment.completed", groupId = "order-service")
  @Transactional
  public void onPaymentCompleted(String payload) {
    try {
      var mapper = new com.fasterxml.jackson.databind.ObjectMapper();
      PaymentCompletedEvent event = mapper.readValue(payload, PaymentCompletedEvent.class);
      var order = repo.findById(event.orderId()).orElseThrow();
      order.setStatus("SUCCESS".equalsIgnoreCase(event.status()) ? "CONFIRMED" : "PAYMENT_FAILED");
      order.setUpdatedAt(java.time.Instant.now());
      repo.save(order);
    } catch (Exception e) {
      throw new IllegalStateException("Failed to process payment.completed", e);
    }
  }
}
