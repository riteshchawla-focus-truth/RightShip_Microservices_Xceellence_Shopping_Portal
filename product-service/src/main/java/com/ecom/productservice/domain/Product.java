package com.ecom.productservice.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "product")
public class Product {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable=false, unique=true, length=80)
  private String sku;
  @Column(nullable=false, length=200)
  private String name;
  @Column(columnDefinition = "text")
  private String description;
  @Column(nullable=false, precision=12, scale=2)
  private BigDecimal price;
  @Column(nullable=false, length=3)
  private String currency = "INR";
  @Column(name = "available_stock", nullable=false)
  private int availableStock;
  @Column(nullable=false)
  private boolean active = true;
  @Column(name="created_at", nullable=false)
  private Instant createdAt = Instant.now();

  public Long getId() { return id; }
  public String getSku() { return sku; }
  public void setSku(String sku) { this.sku = sku; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  public BigDecimal getPrice() { return price; }
  public void setPrice(BigDecimal price) { this.price = price; }
  public String getCurrency() { return currency; }
  public void setCurrency(String currency) { this.currency = currency; }
  public int getAvailableStock() { return availableStock; }
  public void setAvailableStock(int availableStock) { this.availableStock = availableStock; }
  public boolean isActive() { return active; }
  public void setActive(boolean active) { this.active = active; }
  public Instant getCreatedAt() { return createdAt; }
}
