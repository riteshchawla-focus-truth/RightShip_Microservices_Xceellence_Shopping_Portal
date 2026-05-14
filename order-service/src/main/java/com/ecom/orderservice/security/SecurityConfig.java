package com.ecom.orderservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/actuator/**").permitAll()
            .requestMatchers("/api/public/**").permitAll()
            .anyRequest().authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
        .build();
  }

  /**
   * Keycloak puts realm roles under: realm_access.roles (e.g. ["USER","ADMIN"]).
   * This converter maps them to Spring Security authorities: ROLE_USER, ROLE_ADMIN.
   */
  @Bean
  JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
    return converter;
  }

  static class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
      Set<GrantedAuthority> authorities = new HashSet<>();

      // scopes -> SCOPE_xxx (optional)
      Object scope = jwt.getClaims().get("scope");
      if (scope instanceof String s) {
        for (String sc : s.split(" ")) {
          if (!sc.isBlank()) authorities.add(new SimpleGrantedAuthority("SCOPE_" + sc));
        }
      }

      // realm roles -> ROLE_xxx
      Map<String, Object> realmAccess = jwt.getClaim("realm_access");
      if (realmAccess != null) {
        Object rolesObj = realmAccess.get("roles");
        if (rolesObj instanceof Collection<?> roles) {
          for (Object r : roles) {
            if (r != null) authorities.add(new SimpleGrantedAuthority("ROLE_" + r.toString()));
          }
        }
      }

      return authorities;
    }
  }
}
