package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.Contract;
import java.util.function.Consumer;

@FunctionalInterface
public interface CreateContract extends Consumer<Contract> {

}
