package com.uade.propertiesbackend.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Base64;
import java.util.Map;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private final ObjectMapper objectMapper;


  public JwtService(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  private Map extractAllClaims(String token) {
    String body = token.split("\\.")[1];
    byte[] decodedBytes = Base64.getDecoder().decode(body);
    String decodedString = new String(decodedBytes);
    try {
      return objectMapper.readValue(decodedString, Map.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public String extractUserId(String token) {
    return extractAllClaims(token).getOrDefault("cuit", Strings.EMPTY).toString();
  }

  public boolean extractIsAdmin(String token) {
    return Boolean.parseBoolean(
        extractAllClaims(token).getOrDefault("is_admin", Strings.EMPTY).toString());
  }
}
