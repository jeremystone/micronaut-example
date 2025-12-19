package com.things;

import io.micronaut.json.tree.JsonNode;
import io.micronaut.serde.annotation.Serdeable;

import java.time.Instant;

@Serdeable
public record ThingOut(String id, String name, int number, JsonNode data, Instant creationDate) {
}
