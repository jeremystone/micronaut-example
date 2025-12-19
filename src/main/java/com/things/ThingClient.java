package com.things;

import io.micronaut.http.BasicAuth;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Client("/things")
public interface ThingClient {

    @Post
    String create(ThingIn thingIn, BasicAuth basicAuth);

    @Post
    Mono<String> createReactively(ThingIn thingIn, BasicAuth basicAuth);

    @Get("/{id}")
    Optional<ThingOut> get(@PathVariable String id, BasicAuth basicAuth);

    @Get("/{id}")
    Mono<Optional<ThingOut>> getReactively(@PathVariable String id, BasicAuth basicAuth);
}
