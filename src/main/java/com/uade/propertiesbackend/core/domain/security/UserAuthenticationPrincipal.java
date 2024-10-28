package com.uade.propertiesbackend.core.domain.security;

import com.uade.propertiesbackend.core.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserAuthenticationPrincipal {

  private Long id;
  private UserRole role;
}
