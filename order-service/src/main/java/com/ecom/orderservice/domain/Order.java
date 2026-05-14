package com.ecom.orderservice.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name="user_id", nullable=false, length=60)
  private String userId;
  @Column(nullable=false, length=30)
  private String status = "CREATED";
  @Column(name="total_amount", nullable=false, precision=12, scale=2)
  private BigDecimal totalAmount;
  @Column(nullable=false, length=3)
  private String currency = "INR";
  @Column(name="created_at", nullable=false)
  private Instant createdAt = Instant.now();
  @Column(name="updated_at", nullable=false)
  private Instant updatedAt = Instant.now();

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<OrderItem> items = new ArrayList<>();

  public Long getId() { return id; }
  public String getUserId() { return userId; }
  public void setUserId(String userId) { this.userId = userId; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public BigDecimal getTotalAmount() { return totalAmount; }
  public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
  public String getCurrency() { return currency; }
  public void setCurrency(String currency) { this.currency = currency; }
  public List<OrderItem> getItems() { return items; }
  public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
