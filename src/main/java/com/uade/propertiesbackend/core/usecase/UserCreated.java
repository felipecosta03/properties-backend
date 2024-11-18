package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.UserEvent;
import java.util.function.Consumer;

@FunctionalInterface
public interface UserCreated extends Consumer<UserEvent> {

}
