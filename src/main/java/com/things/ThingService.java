package com.things;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class ThingService {
    final ThingRepository repository;
    final ThingMapper mapper;

    @Inject
    public ThingService(ThingRepository repository, ThingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public String create(ThingIn thingIn) {
        var thing = mapper.fromIn(thingIn);
        return repository.save(thing).id();
    }

    public Optional<ThingOut> findById(String id) {
        return repository.findById(id).map(mapper::toOut);
    }

}
