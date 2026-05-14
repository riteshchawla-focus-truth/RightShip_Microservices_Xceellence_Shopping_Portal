package com.ecom.shared.events;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderCreatedEvent(
        String eventId,
        Instant occurredAt,
        Long orderId,
        String userId,
        BigDecimal amount,
        String currency,
        List<OrderItem> items
) {
    public record OrderItem(Long productId, int quantity, BigDecimal unitPrice) {}
}
