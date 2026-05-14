package com.ecom.apigateway.correlation;

import com.ecom.shared.correlation.Correlation;
import org.slf4j.MDC;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class CorrelationWebFilter implements WebFilter {
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    String corrId = request.getHeaders().getFirst(Correlation.HEADER);
    if (corrId == null || corrId.isBlank()) corrId = UUID.randomUUID().toString();
    String finalCorr = corrId;
    MDC.put(Correlation.MDC_KEY, finalCorr);
    return chain.filter(exchange.mutate()
            .request(request.mutate().header(Correlation.HEADER, finalCorr).build())
            .build())
        .doFinally(s -> MDC.remove(Correlation.MDC_KEY));
  }
}
