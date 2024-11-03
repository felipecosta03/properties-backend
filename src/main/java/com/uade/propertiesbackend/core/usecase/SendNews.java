package com.uade.propertiesbackend.core.usecase;

import com.uade.propertiesbackend.core.domain.TopicName;
import java.util.function.BiConsumer;

@FunctionalInterface
public interface SendNews extends BiConsumer<Object, TopicName> {


}
