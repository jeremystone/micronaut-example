package com.events;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Event(String value) {
}
