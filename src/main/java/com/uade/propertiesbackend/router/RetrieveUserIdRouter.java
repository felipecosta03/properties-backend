package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.domain.UserDto;
import com.uade.propertiesbackend.util.SecurityUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Extra", description = "Retrieve user id")
public class RetrieveUserIdRouter {

  @GetMapping("/user")
  public ResponseEntity<UserDto> retrieveUserId() {
    return ResponseEntity.ok(UserDto.builder().id(SecurityUtils.getUserId()).build());
  }
}
