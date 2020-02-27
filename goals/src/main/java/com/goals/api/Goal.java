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
    private final Boolean completed;


    public Goal() {
        this.name = null;
        this.goal = null;
        this.completed = null;
    }

    public Goal(String name, String goal, Boolean completed) {
        this.name = name;
        this.goal = goal;
        this.completed = completed;
    }
}
