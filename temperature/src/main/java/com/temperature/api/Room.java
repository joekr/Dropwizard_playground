package com.temperature.api;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.util.Date;


import lombok.Getter;

public class Room {
  @Getter
  @JsonProperty
  private final int id;

  @Getter
  @JsonProperty
  @NotEmpty
  private final String name;

  @Getter
  @JsonProperty
  @NotEmpty
  private final Timestamp created;

  @Getter
  @JsonProperty
  @NotEmpty
  private final Timestamp modified;

  public Room(int id, String name, Timestamp created, Timestamp modified){
    this.id = id;
    this.name = name;
    this.created = created;
    this.modified = modified;
  }

  public Room() {
    this.id = 0;
    this.name = null;
    this.created = null;
    this.modified = null;
  }
}
