package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.Role;
import com.uade.propertiesbackend.core.domain.dto.RentalsDto;
import java.util.function.Function;
import lombok.Builder;
import lombok.Getter;

public interface RetrieveRentalsByUserId extends
    Function<RetrieveRentalsByUserId.Model, RentalsDto> {

  @Getter
  @Builder
  class Model {

    private Role role;
    private Long userId;
  }
}
