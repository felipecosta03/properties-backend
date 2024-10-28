package com.uade.propertiesbackend.core.config;

import com.uade.propertiesbackend.core.domain.security.UserAuthenticationToken;
import com.uade.propertiesbackend.core.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  public AuthenticationFilter(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String jwt = authHeader.substring(7);
    final Long userId = Long.valueOf(jwtService.extractUsername(jwt));
    final String role = jwtService.extractRole(jwt);

    SecurityContextHolder.getContext().setAuthentication(new UserAuthenticationToken(userId, role));

    filterChain.doFilter(request, response);
  }
}
