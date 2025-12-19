package com.things;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.json.tree.JsonNode;

import java.time.Instant;

@MappedEntity
public record Thing(@Id @GeneratedValue String id, String name, int number, JsonNode data, Instant creationDate) {
}
