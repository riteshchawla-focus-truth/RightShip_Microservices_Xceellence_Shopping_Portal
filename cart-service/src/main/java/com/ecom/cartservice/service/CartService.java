package com.ecom.cartservice.service;

import com.ecom.cartservice.domain.Cart;
import com.ecom.cartservice.domain.CartItem;
import com.ecom.cartservice.repo.CartItemRepository;
import com.ecom.cartservice.repo.CartRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class CartService {

  private final CartRepository cartRepo;
  private final CartItemRepository itemRepo;
  private final WebClient webClient;

  public CartService(CartRepository cartRepo, CartItemRepository itemRepo, WebClient.Builder builder) {
    this.cartRepo = cartRepo;
    this.itemRepo = itemRepo;
    this.webClient = builder.build();
  }

  @Transactional
  public Cart addItem(String userId, Long productId, int qty, String bearerToken) {
    Cart cart = cartRepo.findByUserIdAndStatus(userId, "ACTIVE").orElseGet(() -> cartRepo.save(Cart.active(userId)));

    ProductDto product = fetchProduct(productId, bearerToken);
    if (product == null) throw new IllegalStateException("Product not found");

    CartItem item = itemRepo.findByCartIdAndProductId(cart.getId(), productId)
        .orElseGet(() -> itemRepo.save(CartItem.create(cart, productId, 0, product.price(), product.currency())));

    item.setQuantity(item.getQuantity() + qty);
    itemRepo.save(item);

    cart.setUpdatedAt(Instant.now());
    return cartRepo.save(cart);
  }

  @CircuitBreaker(name = "productService", fallbackMethod = "productFallback")
  public ProductDto fetchProduct(Long productId, String bearerToken) {
    return webClient.get()
        .uri("lb://product-service/api/products/{id}", productId)
        .header("Authorization", bearerToken)
        .retrieve()
        .bodyToMono(ProductDto.class)
        .block();
  }

  public ProductDto productFallback(Long productId, String bearerToken, Throwable t) { return null; }

  public Cart getActive(String userId) {
    return cartRepo.findByUserIdAndStatus(userId, "ACTIVE").orElseGet(() -> cartRepo.save(Cart.active(userId)));
  }

  @Transactional
  public void clear(String userId) {
    cartRepo.findByUserIdAndStatus(userId, "ACTIVE").ifPresent(c -> itemRepo.deleteByCartId(c.getId()));
  }

  public record ProductDto(Long id, BigDecimal price, String currency) {}
}
