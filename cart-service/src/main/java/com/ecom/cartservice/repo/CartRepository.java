package com.ecom.cartservice.repo;

import com.ecom.cartservice.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
  Optional<Cart> findByUserIdAndStatus(String userId, String status);
}
