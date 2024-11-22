package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.PaymentEvent;
import java.util.function.Consumer;

@FunctionalInterface
public interface RentPaid extends Consumer<PaymentEvent> {

}
