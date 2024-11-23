package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.Contract;
import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface RetrieveContractById extends Function<String, Optional<Contract>> {

}
