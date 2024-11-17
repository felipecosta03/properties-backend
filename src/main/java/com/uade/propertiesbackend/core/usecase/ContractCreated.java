package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.ContractEvent;
import java.util.function.Consumer;

@FunctionalInterface
public interface ContractCreated extends Consumer<ContractEvent> {

}
