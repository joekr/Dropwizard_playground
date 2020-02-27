package com.goals.core;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
public class GoalDo {

    private int id;
    private int userId;
    private final String name;
    private final String goal;
    private final Timestamp completedAt;
    private final Timestamp createdAt;
    private final Timestamp modifiedAt;

    public GoalDo(int id, int userId, String name, String goal, Timestamp completedAt, Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.goal = goal;
        this.completedAt = completedAt;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
