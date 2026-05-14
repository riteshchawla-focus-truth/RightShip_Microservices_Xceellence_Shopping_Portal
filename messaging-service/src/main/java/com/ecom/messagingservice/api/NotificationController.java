package com.ecom.messagingservice.api;

import com.ecom.messagingservice.repo.EventLogRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
  private final EventLogRepository repo;
  public NotificationController(EventLogRepository repo) { this.repo = repo; }

  @GetMapping("/events")
  @PreAuthorize("hasRole('ADMIN')")
  public Object logs() { return repo.findAll(); }
}
