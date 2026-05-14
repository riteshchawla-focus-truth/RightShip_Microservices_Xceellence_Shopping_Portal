package com.ecom.shared.outbox;

public final class OutboxTopics {
  private OutboxTopics() {}
  public static final String ORDER_CREATED = "order.created";
  public static final String PAYMENT_COMPLETED = "payment.completed";
  public static final String NOTIFICATION_EVENT = "notification.event";
}
