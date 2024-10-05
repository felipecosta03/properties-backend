package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.dto.PropertyDto;
import com.uade.propertiesbackend.core.usecase.RetrieveFavoriteProperties.Model;
import java.util.function.Function;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@FunctionalInterface
public interface RetrieveFavoriteProperties extends Function<Model, Page<PropertyDto>> {

  @Getter
  @Builder
  class Model {
    private Long userId;
    private Integer page;
    private Integer size;
  }
}
