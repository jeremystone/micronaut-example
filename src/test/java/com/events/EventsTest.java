package com.events;

import io.micronaut.messaging.annotation.MessageListener;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Hooks;
import reactor.test.StepVerifier;

import java.time.Duration;

@MicronautTest
public class EventsTest {
    @Inject
    QueueListener queueListener;
    @Inject
    QueuePublisher queuePublisher;

    @BeforeAll
    static void beforeAll() {
        Hooks.onOperatorDebug();
    }

    @Test
    void messages_are_sent_and_received() throws InterruptedException {
        var verifier = StepVerifier.create(queueListener.flux)
                .expectNext(new QueueListener.ReceivedEvent("id1", new Event("event1"))).as("e1")
                .expectNext(new QueueListener.ReceivedEvent("id2", new Event("event2"))).as("e2")
                .thenCancel();

        queuePublisher.sendEvent("id1", new Event("event1"));
        queuePublisher.sendEvent("id2", new Event("event2"));

        verifier.verify(Duration.ofSeconds(1));
    }
}
