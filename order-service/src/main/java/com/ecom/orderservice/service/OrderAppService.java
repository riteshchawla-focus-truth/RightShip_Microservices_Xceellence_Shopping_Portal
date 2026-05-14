package com.ecom.orderservice.service;

import com.ecom.orderservice.domain.Order;
import com.ecom.orderservice.domain.OrderItem;
import com.ecom.orderservice.outbox.OutboxService;
import com.ecom.orderservice.repo.OrderRepository;
import com.ecom.shared.events.OrderCreatedEvent;
import com.ecom.shared.outbox.OutboxTopics;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class OrderAppService {

  private final OrderRepository repo;
  private final OutboxService outbox;
  private final WebClient webClient;

  public OrderAppService(OrderRepository repo, OutboxService outbox, WebClient.Builder builder) {
    this.repo = repo;
    this.outbox = outbox;
    this.webClient = builder.build();
  }

  @Transactional
  public Order createFromCart(String userId, String bearerToken) {
    CartDto cart = fetchCart(bearerToken);
    if (cart == null || cart.items() == null || cart.items().isEmpty()) throw new IllegalStateException("Cart is empty");

    Order o = new Order();
    o.setUserId(userId);
    BigDecimal total = BigDecimal.ZERO;

    for (CartItemDto ci : cart.items()) {
      OrderItem oi = new OrderItem();
      oi.setOrder(o);
      oi.setProductId(ci.productId());
      oi.setQuantity(ci.quantity());
      oi.setUnitPrice(ci.unitPrice());
      o.getItems().add(oi);
      total = total.add(ci.unitPrice().multiply(BigDecimal.valueOf(ci.quantity())));
    }

    o.setTotalAmount(total);
    o.setCurrency(cart.currency() == null ? "INR" : cart.currency());

    Order saved = repo.save(o);

    List<OrderCreatedEvent.OrderItem> items = saved.getItems().stream()
        .map(i -> new OrderCreatedEvent.OrderItem(i.getProductId(), i.getQuantity(), i.getUnitPrice()))
        .toList();

    OrderCreatedEvent event = new OrderCreatedEvent(null, Instant.now(), saved.getId(), userId,
        saved.getTotalAmount(), saved.getCurrency(), items);

    outbox.saveEvent("Order", saved.getId().toString(), "OrderCreated", OutboxTopics.ORDER_CREATED,
        saved.getId().toString(), event);

    return saved;
  }

  @CircuitBreaker(name = "cartService", fallbackMethod = "cartFallback")
  public CartDto fetchCart(String bearerToken) {
    return webClient.get()
        .uri("lb://cart-service/api/cart")
        .header("Authorization", bearerToken)
        .retrieve()
        .bodyToMono(CartDto.class)
        .block();
  }

  public CartDto cartFallback(String bearerToken, Throwable t) { return null; }

  public record CartDto(Long id, String userId, String status, String currency, List<CartItemDto> items) {}
  public record CartItemDto(Long productId, int quantity, java.math.BigDecimal unitPrice, String currency) {}
}
