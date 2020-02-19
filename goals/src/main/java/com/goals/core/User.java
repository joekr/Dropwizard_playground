package com.goals.core;

import java.security.Principal;
import java.util.Set;

public class User implements Principal {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public User(String name, Set<String> roles) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return (int) (Math.random() * 100);
    }
}
