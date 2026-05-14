package com.ecom.cartservice.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name="user_id", nullable=false, length=60)
  private String userId;
  @Column(nullable=false, length=20)
  private String status = "ACTIVE";
  @Column(name="created_at", nullable=false)
  private Instant createdAt = Instant.now();
  @Column(name="updated_at", nullable=false)
  private Instant updatedAt = Instant.now();

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<CartItem> items = new ArrayList<>();

  public static Cart active(String userId) {
    Cart c = new Cart();
    c.userId = userId;
    c.status = "ACTIVE";
    return c;
  }

  public Long getId() { return id; }
  public String getUserId() { return userId; }
  public List<CartItem> getItems() { return items; }
  public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
