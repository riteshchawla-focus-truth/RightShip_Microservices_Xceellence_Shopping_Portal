package com.ecom.userservice.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user_profile")
public class UserProfile {
  @Id
  @Column(name = "user_id", length = 60)
  private String userId;
  @Column(length = 200)
  private String email;
  @Column(name = "full_name", length = 200)
  private String fullName;
  @Column(length = 40)
  private String phone;
  @Column(name = "created_at", nullable = false)
  private Instant createdAt = Instant.now();

  public String getUserId() { return userId; }
  public void setUserId(String userId) { this.userId = userId; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getFullName() { return fullName; }
  public void setFullName(String fullName) { this.fullName = fullName; }
  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }
  public Instant getCreatedAt() { return createdAt; }
}
