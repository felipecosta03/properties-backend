package com.uade.propertiesbackend.core.usecase;

import lombok.Builder;
import lombok.Getter;

import java.util.function.Predicate;

public interface PropertyIsFavorite extends Predicate<PropertyIsFavorite.Model> {
    @Getter
    @Builder
    class Model{
        Long propertyId;
        Long userId;
    }
}
