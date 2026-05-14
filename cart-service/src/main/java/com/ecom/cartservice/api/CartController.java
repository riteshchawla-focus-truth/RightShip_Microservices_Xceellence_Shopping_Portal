package com.ecom.cartservice.api;

import com.ecom.cartservice.domain.Cart;
import com.ecom.cartservice.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

  private final CartService service;
  public CartController(CartService service) { this.service = service; }

  public record AddItemReq(Long productId, int quantity) {}

  @PostMapping("/items")
  public Cart add(@RequestBody AddItemReq req, Authentication auth, @RequestHeader("Authorization") String bearer) {
    Jwt jwt = (Jwt) auth.getPrincipal();
    return service.addItem(jwt.getSubject(), req.productId(), req.quantity(), bearer);
  }

  @GetMapping
  public Cart get(Authentication auth) {
    Jwt jwt = (Jwt) auth.getPrincipal();
    return service.getActive(jwt.getSubject());
  }

  @DeleteMapping("/clear")
  public void clear(Authentication auth) {
    Jwt jwt = (Jwt) auth.getPrincipal();
    service.clear(jwt.getSubject());
  }
}
