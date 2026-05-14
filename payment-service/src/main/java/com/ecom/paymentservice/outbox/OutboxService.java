package com.ecom.paymentservice.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OutboxService {
  private final OutboxEventRepository repo;
  private final ObjectMapper mapper;
  public OutboxService(OutboxEventRepository repo, ObjectMapper mapper) {
    this.repo = repo;
    this.mapper = mapper;
  }

  public String saveEvent(String aggregateType, String aggregateId, String eventType,
                         String topic, String key, Object payload) {
    String eventId = UUID.randomUUID().toString();
    try {
      String json = mapper.writeValueAsString(payload);
      repo.save(OutboxEvent.newEvent(eventId, aggregateType, aggregateId, eventType, topic, key, json));
      return eventId;
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Failed to serialize outbox payload", e);
    }
  }
}
