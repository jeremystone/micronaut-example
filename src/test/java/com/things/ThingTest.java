package com.things;

import com.config.SecurityConfig;
import io.micronaut.json.JsonMapper;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class ThingTest {
    @Inject
    ThingClient thingClient;

    @Inject
    JsonMapper jsonMapper;

    @Inject
    SecurityConfig securityConfig;

    @SneakyThrows
    @Test
    void postAndGet() {
        var data = jsonMapper.readValue("""
                { "someData": "value" }
                """, JsonNode.class);
        var thingIn1 = ThingIn.builder().name("AAA").number(1).data(data).build();
        var thingIn2 = ThingIn.builder().name("BBB").number(2).data(data).build();

        var id1 = thingClient.create(thingIn1,securityConfig.basicAuth());
        thingClient.create(thingIn2, securityConfig.basicAuth());

        var thingOut1 = thingClient.get(id1,securityConfig.basicAuth());

        assertThat(thingOut1).hasValueSatisfying(to1 -> {
            assertThat(to1.name()).isEqualTo("AAA");
            assertThat(to1.number()).isEqualTo(1);
            assertThat(to1.data()).isEqualTo(data);
            assertThat(to1.id()).isEqualTo(id1);
        });
    }


    @Test
    @SneakyThrows
    void postAndGetReactively() {
        var data = jsonMapper.readValue("""
                { "someData": "value" }
                """, JsonNode.class);

        var thingIn1 = ThingIn.builder().name("AAA").number(1).data(data).build();

        var thingOut1 = thingClient.createReactively(thingIn1,securityConfig.basicAuth())
                .flatMap(id -> thingClient.getReactively(id,securityConfig.basicAuth())).block();

        assertThat(thingOut1).hasValueSatisfying(to1 -> {
            assertThat(to1.name()).isEqualTo("AAA");
            assertThat(to1.number()).isEqualTo(1);
            assertThat(to1.data()).isEqualTo(data);
        });
    }


    @Test
    void getMissing() {
        var thingOut1 = thingClient.get("12345c6c1f0ab77948f27faa",securityConfig.basicAuth());

        assertThat(thingOut1).isNotPresent();
    }
}
