package com.ecom.messagingservice.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "event_log")
public class EventLog {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable=false, length=120)
  private String topic;
  @Lob
  @Column(nullable=false, columnDefinition="longtext")
  private String payload;
  @Column(name="received_at", nullable=false)
  private Instant receivedAt = Instant.now();

  public Long getId() { return id; }
  public String getTopic() { return topic; }
  public void setTopic(String topic) { this.topic = topic; }
  public String getPayload() { return payload; }
  public void setPayload(String payload) { this.payload = payload; }
}
