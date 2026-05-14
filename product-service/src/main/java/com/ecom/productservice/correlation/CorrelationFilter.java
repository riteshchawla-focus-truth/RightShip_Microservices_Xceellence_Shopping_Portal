package com.ecom.productservice.correlation;

import com.ecom.shared.correlation.Correlation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String corrId = request.getHeader(Correlation.HEADER);
    if (corrId == null || corrId.isBlank()) corrId = UUID.randomUUID().toString();
    MDC.put(Correlation.MDC_KEY, corrId);
    response.setHeader(Correlation.HEADER, corrId);
    try {
      filterChain.doFilter(request, response);
    } finally {
      MDC.remove(Correlation.MDC_KEY);
    }
  }
}
