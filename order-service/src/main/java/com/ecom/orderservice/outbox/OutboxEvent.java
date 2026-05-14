package com.ecom.orderservice.outbox;

import com.ecom.shared.outbox.OutboxStatus;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "outbox_event")
public class OutboxEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "event_id", nullable = false, unique = true, length = 80)
  private String eventId;

  @Column(name = "aggregate_type", nullable = false, length = 60)
  private String aggregateType;

  @Column(name = "aggregate_id", nullable = false, length = 80)
  private String aggregateId;

  @Column(name = "event_type", nullable = false, length = 120)
  private String eventType;

  @Column(name = "topic", nullable = false, length = 120)
  private String topic;

  @Column(name = "msg_key", length = 120)
  private String key;

  @Lob
  @Column(name = "payload", nullable = false, columnDefinition = "longtext")
  private String payload;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  private OutboxStatus status = OutboxStatus.NEW;

  @Column(name = "attempts", nullable = false)
  private int attempts = 0;

  @Column(name = "last_error", columnDefinition = "text")
  private String lastError;

  @Column(name = "available_at", nullable = false)
  private Instant availableAt = Instant.now();

  @Column(name = "created_at", nullable = false)
  private Instant createdAt = Instant.now();

  @Column(name = "sent_at")
  private Instant sentAt;

  @Version
  private long version;

  public static OutboxEvent newEvent(String eventId, String aggregateType, String aggregateId, String eventType,
                                    String topic, String key, String payload) {
    OutboxEvent e = new OutboxEvent();
    e.eventId = eventId;
    e.aggregateType = aggregateType;
    e.aggregateId = aggregateId;
    e.eventType = eventType;
    e.topic = topic;
    e.key = key;
    e.payload = payload;
    e.status = OutboxStatus.NEW;
    e.availableAt = Instant.now();
    e.createdAt = Instant.now();
    return e;
  }

  public String getEventId() { return eventId; }
  public String getTopic() { return topic; }
  public String getKey() { return key; }
  public String getPayload() { return payload; }
  public int getAttempts() { return attempts; }
  public void setAttempts(int attempts) { this.attempts = attempts; }
  public void setLastError(String lastError) { this.lastError = lastError; }
  public void setAvailableAt(Instant availableAt) { this.availableAt = availableAt; }
  public void setSentAt(Instant sentAt) { this.sentAt = sentAt; }
  public void setStatus(OutboxStatus status) { this.status = status; }
}
