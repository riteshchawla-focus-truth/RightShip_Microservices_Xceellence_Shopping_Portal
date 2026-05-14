package com.ecom.messagingservice.repo;

import com.ecom.messagingservice.domain.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepository extends JpaRepository<EventLog, Long> {
}
