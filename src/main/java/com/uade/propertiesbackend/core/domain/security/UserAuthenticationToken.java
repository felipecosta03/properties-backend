package com.uade.propertiesbackend.core.domain.security;

import java.io.Serial;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Getter
public class UserAuthenticationToken extends AbstractAuthenticationToken {

  @Serial
  private static final long serialVersionUID = -6109605722681055968L;

  private final UserAuthenticationPrincipal principal;

  public UserAuthenticationToken(Long userId, boolean isAdmin) {
    super(null);

    this.principal = new UserAuthenticationPrincipal(userId, isAdmin);
    setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public UserAuthenticationPrincipal getPrincipal() {
    return principal;
  }
}
