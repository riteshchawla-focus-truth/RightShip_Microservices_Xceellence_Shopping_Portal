package com.ecom.orderservice.outbox;

import com.ecom.shared.outbox.OutboxStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
public class OutboxPublisher {
  private static final Logger log = LoggerFactory.getLogger(OutboxPublisher.class);
  private final OutboxEventRepository repo;
  private final KafkaTemplate<String, String> kafka;

  public OutboxPublisher(OutboxEventRepository repo, KafkaTemplate<String, String> kafka) {
    this.repo = repo;
    this.kafka = kafka;
  }

  @Scheduled(fixedDelayString = "${outbox.publisher.delay-ms:2000}")
  @Transactional
  public void publish() {
    List<OutboxEvent> batch = repo.findBatchForPublish(OutboxStatus.NEW, Instant.now(), PageRequest.of(0, 50));
    for (OutboxEvent e : batch) {
      try {
        kafka.send(e.getTopic(), e.getKey() != null ? e.getKey() : e.getEventId(), e.getPayload()).get();
        e.setStatus(OutboxStatus.SENT);
        e.setSentAt(Instant.now());
      } catch (Exception ex) {
        int attempts = e.getAttempts() + 1;
        e.setAttempts(attempts);
        e.setStatus(OutboxStatus.NEW);
        e.setLastError(ex.getMessage());
        e.setAvailableAt(Instant.now().plus(Duration.ofSeconds(Math.min(60, attempts * 5L))));
        log.warn("Outbox publish failed eventId=" + e.getEventId() + ", attempts=" + attempts + ", err=" + ex);
      }
    }
  }
}
