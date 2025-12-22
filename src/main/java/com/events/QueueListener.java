package com.events;

import io.micronaut.messaging.annotation.MessageHeader;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitClient;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Singleton
@RabbitClient("event")
public class QueueListener {

    public record ReceivedEvent(String correlationId, Event event){
    }

    // Mainly so that we can test...
    private final Sinks.Many<ReceivedEvent> sink = Sinks.many().multicast().onBackpressureBuffer();

    final Flux<ReceivedEvent> flux = sink.asFlux();

    @Queue("events.main")
    public void handle(@MessageHeader String correlationId, Event event) {
        System.out.println("Received event: " + event + " with correlationId: " + correlationId);

        var eventWithCorrelationId = new ReceivedEvent(correlationId, event);
        System.out.println(sink.tryEmitNext(eventWithCorrelationId));
    }
}
