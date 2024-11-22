package com.uade.propertiesbackend.util;

import com.uade.propertiesbackend.core.domain.security.UserAuthenticationPrincipal;
import com.uade.propertiesbackend.core.domain.security.UserAuthenticationToken;
import java.util.Optional;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

  private SecurityUtils() {

  }

  public static Optional<UserAuthenticationPrincipal> getPrincipal() {
    if (!(SecurityContextHolder.getContext()
        .getAuthentication() instanceof UserAuthenticationToken)) {
      return Optional.empty();
    }
    return Optional.of(
        (UserAuthenticationPrincipal) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal());
  }

  public static Long getUserId() {
    return getPrincipal().map(UserAuthenticationPrincipal::getId).orElse(null);
  }
}
