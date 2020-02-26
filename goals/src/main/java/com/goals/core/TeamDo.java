package com.goals.core;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
public class TeamDo {

    private int id;
    private final String name;
    private final Timestamp createdAt;
    private final Timestamp modifiedAt;

    public TeamDo(int id, String name, Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
