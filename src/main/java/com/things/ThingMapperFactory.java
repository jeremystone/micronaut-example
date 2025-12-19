package com.things;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.mapstruct.factory.Mappers;

@Factory
public class ThingMapperFactory {

    @Bean
    ThingMapper thingMapper(){
        return Mappers.getMapper(ThingMapper.class);
    }

}
