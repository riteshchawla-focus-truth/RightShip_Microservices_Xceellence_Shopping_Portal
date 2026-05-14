package com.ecom.cartservice.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="cart_item", uniqueConstraints=@UniqueConstraint(columnNames={"cart_id","product_id"}))
public class CartItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false)
  @JoinColumn(name="cart_id")
  private Cart cart;

  @Column(name="product_id", nullable=false)
  private Long productId;

  @Column(nullable=false)
  private int quantity;

  @Column(name="unit_price", nullable=false, precision=12, scale=2)
  private BigDecimal unitPrice;

  @Column(nullable=false, length=3)
  private String currency = "INR";

  public static CartItem create(Cart cart, Long productId, int qty, BigDecimal price, String currency) {
    CartItem i = new CartItem();
    i.cart = cart;
    i.productId = productId;
    i.quantity = qty;
    i.unitPrice = price;
    i.currency = currency;
    return i;
  }

  public Long getId() { return id; }
  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) { this.quantity = quantity; }
  public BigDecimal getUnitPrice() { return unitPrice; }
  public String getCurrency() { return currency; }
}
