package com.uade.propertiesbackend.router;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.CreateProperty;
import com.uade.propertiesbackend.router.request.PropertyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatePropertyRouter {
  private final CreateProperty createProperty;

  public CreatePropertyRouter(CreateProperty createProperty) {
    this.createProperty = createProperty;
  }

  @PostMapping("/properties")
  public ResponseEntity<PropertyDto> createProperty(@RequestBody PropertyRequest propertyRequest){
    return null;
  }
}
