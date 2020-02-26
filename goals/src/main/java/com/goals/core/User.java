package com.goals.core;

import lombok.Getter;

import java.security.Principal;
import java.util.Set;

@Getter
public class User implements Principal {
    private final int id;
    private final String name;
    private final int teamId;

    public User(int id, String name, int teamId) {
        this.id = id;
        this.name = name;
        this.teamId = teamId;
    }

//    public String getName() {
//        return name;
//    }
//
//    public int getId() {
//        return (int) (Math.random() * 100);
//    }
}
