package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.Rent;
import java.util.function.Function;

@FunctionalInterface
public interface RetrieveRentByRentProcessId extends Function<Long, Rent> {

}
