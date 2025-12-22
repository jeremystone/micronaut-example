package com.events;

import io.micronaut.messaging.annotation.MessageHeader;
import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;
import io.micronaut.rabbitmq.annotation.RabbitHeaders;

@RabbitClient("event")
public interface QueuePublisher {
    @Binding("events.main")
    void sendEvent(@MessageHeader String correlationId,  Event event);
}
