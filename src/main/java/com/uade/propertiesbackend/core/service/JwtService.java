package com.uade.propertiesbackend.core.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  public static final String ROLE = "role";

  @Value("${security.jwt.secret-key}")
  private String secretKey;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public boolean isTokenValid(String token) {
    return !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return new Date().before(extractExpiration(token));
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token)
        .getBody();
  }

  private Key getSignInKey() {
    return Keys.hmacShaKeyFor(secretKey.getBytes());
  }

  public String extractRole(String token) {
    return extractAllClaims(token).getOrDefault(ROLE, Strings.EMPTY).toString();
  }
}
