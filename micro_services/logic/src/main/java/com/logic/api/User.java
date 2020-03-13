package com.logic.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.security.Principal;
import java.sql.Timestamp;

@Getter
public class User implements Principal {
    private int id;
    @JsonProperty("email")
    private final String name;
    private final String confirmationToken;
    private final Timestamp confirmedAt;
    private final Timestamp lastLoginAt;
    private final Timestamp createdAt;
    private final Timestamp modifiedAt;

    public User() {
        this.id = 0;
        this.name = null;
        this.confirmationToken = null;
        this.confirmedAt = null;
        this.lastLoginAt = null;
        this.createdAt = null;
        this.modifiedAt = null;
    }

    public User(int id, String name, String confirmationToken, Timestamp confirmedAt, Timestamp lastLoginAt, Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.name = name;
        this.confirmationToken = confirmationToken;
        this.confirmedAt = confirmedAt;
        this.lastLoginAt = lastLoginAt;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
