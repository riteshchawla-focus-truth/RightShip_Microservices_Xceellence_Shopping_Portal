package com.ecom.shared.correlation;

public final class Correlation {
  private Correlation() {}
  public static final String HEADER = "X-Correlation-Id";
  public static final String MDC_KEY = "correlationId";
}
