package com.token.models;

import com.google.common.collect.Maps;

import java.util.Map;

public class UserTransfer {

    private final String name;
    private final Map<String, Boolean> roles;
    private final String token;

    public UserTransfer(String name, Map<String, Boolean> roles, String token) {
        this.name = name;
        this.roles = Maps.newConcurrentMap();
        this.roles.putAll(roles);
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public Map<String, Boolean> getRoles() {
        return roles;
    }

    public String getToken() {
        return token;
    }
}
