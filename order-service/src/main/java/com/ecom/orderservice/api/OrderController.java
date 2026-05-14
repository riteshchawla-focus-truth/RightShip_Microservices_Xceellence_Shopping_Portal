package com.ecom.orderservice.api;

import com.ecom.orderservice.domain.Order;
import com.ecom.orderservice.repo.OrderRepository;
import com.ecom.orderservice.service.OrderAppService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderRepository repo;
  private final OrderAppService service;

  public OrderController(OrderRepository repo, OrderAppService service) {
    this.repo = repo;
    this.service = service;
  }

  @PostMapping
  public Order place(Authentication auth, @RequestHeader("Authorization") String bearer) {
    String userId = ((Jwt) auth.getPrincipal()).getSubject();
    return service.createFromCart(userId, bearer);
  }

  @GetMapping
  public List<Order> my(Authentication auth) {
    String userId = ((Jwt) auth.getPrincipal()).getSubject();
    return repo.findByUserId(userId);
  }
}
