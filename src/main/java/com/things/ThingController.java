package com.things;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/things")
public class ThingController {

    final ThingService thingService;

    public ThingController(ThingService thingService) {
        this.thingService = thingService;
    }

    @Post
    public HttpResponse<String> create(ThingIn thingIn) {
        var id = thingService.create(thingIn);

        return HttpResponse.created(id);
    }

    @Get("/{id}")
    public HttpResponse<ThingOut> get(@PathVariable String id) {
        var maybeThing = thingService.findById(id);

        if (maybeThing.isPresent()) {
            return HttpResponse.ok(maybeThing.get());
        } else {
            return HttpResponse.notFound();
        }
    }
}
