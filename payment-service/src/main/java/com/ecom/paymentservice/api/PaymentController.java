package com.ecom.paymentservice.api;

import com.ecom.paymentservice.domain.Payment;
import com.ecom.paymentservice.repo.PaymentRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
  private final PaymentRepository repo;
  public PaymentController(PaymentRepository repo) { this.repo = repo; }

  @GetMapping("/order/{orderId}")
  public Payment byOrder(@PathVariable Long orderId) { return repo.findByOrderId(orderId).orElseThrow(); }
}
