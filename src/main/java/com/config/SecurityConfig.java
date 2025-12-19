package com.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.http.BasicAuth;

@ConfigurationProperties("security")
public record SecurityConfig(String username, String password) {
    public BasicAuth basicAuth() {
        return new BasicAuth(username, password);
    }
}