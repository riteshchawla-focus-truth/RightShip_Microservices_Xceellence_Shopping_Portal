package com.ecom.apigateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.*;

@Configuration
public class GatewaySecurityConfig {

  @Bean
  SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    return http
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .authorizeExchange(ex -> ex
            .pathMatchers("/actuator/**").permitAll()
            .pathMatchers("/api/public/**").permitAll()
            .anyExchange().authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(reactiveJwtAuthConverter())))
        .build();
  }

  @Bean
  public Converter<Jwt, ? extends org.reactivestreams.Publisher<? extends AbstractAuthenticationToken>> reactiveJwtAuthConverter() {
    JwtAuthenticationConverter delegate = new JwtAuthenticationConverter();
    delegate.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
    return new ReactiveJwtAuthenticationConverterAdapter(delegate);
  }

  static class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
      Set<GrantedAuthority> authorities = new HashSet<>();

      Object scope = jwt.getClaims().get("scope");
      if (scope instanceof String s) {
        for (String sc : s.split(" ")) {
          if (!sc.isBlank()) authorities.add(new SimpleGrantedAuthority("SCOPE_" + sc));
        }
      }

      Map<String, Object> realmAccess = jwt.getClaim("realm_access");
      if (realmAccess != null) {
        Object rolesObj = realmAccess.get("roles");
        if (rolesObj instanceof Collection<?> roles) {
          for (Object r : roles) {
            if (r != null) authorities.add(new SimpleGrantedAuthority("ROLE_" + r));
          }
        }
      }

      return authorities;
    }
  }
}
