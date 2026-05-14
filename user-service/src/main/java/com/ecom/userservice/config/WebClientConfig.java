package com.ecom.userservice.config;

import com.ecom.shared.correlation.Correlation;
import org.slf4j.MDC;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {
  @Bean
  @LoadBalanced
  public WebClient.Builder webClientBuilder() {
    return WebClient.builder().filter(ExchangeFilterFunction.ofRequestProcessor(req -> {
      String corr = MDC.get(Correlation.MDC_KEY);
      if (corr != null) {
        return Mono.just(org.springframework.web.reactive.function.client.ClientRequest.from(req)
            .header(Correlation.HEADER, corr)
            .build());
      }
      return Mono.just(req);
    }));
  }
}
