package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.Rent;
import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface RetrieveRentalsByPropertyId extends Function<Long, List<Rent>> {

}
