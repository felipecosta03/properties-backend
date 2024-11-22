package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.ContractEvent;
import java.util.function.Consumer;

@FunctionalInterface
public interface ContractCancelled extends Consumer<ContractEvent> {

}
