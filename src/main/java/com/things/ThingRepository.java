package com.things;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;

@MongoRepository
public interface ThingRepository extends CrudRepository<Thing, String> {
}
