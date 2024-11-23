package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.PropertySortBy;
import java.util.function.Function;
import org.springframework.data.domain.Sort;

@FunctionalInterface
public interface RetrievePropertySort extends Function<PropertySortBy, Sort> {

}
