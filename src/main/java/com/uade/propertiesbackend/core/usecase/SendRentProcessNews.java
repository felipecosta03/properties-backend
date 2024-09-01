package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.RentProcess;
import java.util.function.Consumer;

@FunctionalInterface
public interface SendRentProcessNews extends Consumer<RentProcess> {

}
