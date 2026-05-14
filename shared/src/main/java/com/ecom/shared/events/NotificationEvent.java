package com.ecom.shared.events;

import java.time.Instant;

public record NotificationEvent(
        String eventId,
        Instant occurredAt,
        String type,
        String to,
        String subject,
        String message
) {}
