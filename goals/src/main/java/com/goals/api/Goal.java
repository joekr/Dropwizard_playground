package com.goals.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Goal {

    @JsonProperty
    private String name;

    @JsonProperty
    private String goal;

    @JsonProperty
    private final Timestamp completedAt;

    public Goal() {
        this.name = null;
        this.goal = null;
        this.completedAt = null;
    }

    public Goal(String name, String goal, Timestamp completedAt) {
        this.name = name;
        this.goal = goal;
        this.completedAt = completedAt;
    }
}
