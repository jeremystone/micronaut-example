package com.things;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ThingMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", expression = "java(Instant.now())")
    Thing fromIn(ThingIn in);

    ThingOut toOut(Thing thing);
}
