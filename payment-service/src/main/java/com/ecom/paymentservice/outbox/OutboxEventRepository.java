package com.ecom.paymentservice.outbox;

import com.ecom.shared.outbox.OutboxStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, Long> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("select e from OutboxEvent e where e.status = :status and e.availableAt <= :now order by e.id")
  List<OutboxEvent> findBatchForPublish(@Param("status") OutboxStatus status,
                                       @Param("now") Instant now,
                                       Pageable pageable);
}
