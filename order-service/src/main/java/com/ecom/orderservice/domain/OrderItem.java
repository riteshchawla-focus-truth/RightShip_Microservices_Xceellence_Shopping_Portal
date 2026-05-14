package com.ecom.orderservice.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
public class OrderItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(optional=false)
  @JoinColumn(name="order_id")
  private Order order;
  @Column(name="product_id", nullable=false)
  private Long productId;
  @Column(nullable=false)
  private int quantity;
  @Column(name="unit_price", nullable=false, precision=12, scale=2)
  private BigDecimal unitPrice;

  public void setOrder(Order order) { this.order = order; }
  public Long getProductId() { return productId; }
  public void setProductId(Long productId) { this.productId = productId; }
  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) { this.quantity = quantity; }
  public BigDecimal getUnitPrice() { return unitPrice; }
  public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}
