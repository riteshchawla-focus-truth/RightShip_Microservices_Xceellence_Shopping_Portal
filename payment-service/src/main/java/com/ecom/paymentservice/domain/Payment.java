package com.ecom.paymentservice.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payment")
public class Payment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name="order_id", nullable=false)
  private Long orderId;
  @Column(nullable=false, precision=12, scale=2)
  private BigDecimal amount;
  @Column(nullable=false, length=3)
  private String currency;
  @Column(nullable=false, length=20)
  private String status = "INITIATED";
  @Column(name="provider_ref", length=200)
  private String providerRef;
  @Column(name="created_at", nullable=false)
  private Instant createdAt = Instant.now();
  @Column(name="updated_at", nullable=false)
  private Instant updatedAt = Instant.now();

  public static Payment initiated(Long orderId, BigDecimal amount, String currency) {
    Payment p = new Payment();
    p.orderId = orderId;
    p.amount = amount;
    p.currency = currency;
    return p;
  }

  public Long getId() { return id; }
  public Long getOrderId() { return orderId; }
  public BigDecimal getAmount() { return amount; }
  public String getCurrency() { return currency; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public String getProviderRef() { return providerRef; }
  public void setProviderRef(String providerRef) { this.providerRef = providerRef; }
  public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
