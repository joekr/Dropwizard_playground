package com.temperature.api;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.validation.ValidationMethod;

import java.sql.Timestamp;
import java.util.Date;


import lombok.Getter;

public class Sensor {
  @Getter
  @JsonProperty
  private final int id;

  @Getter
  @JsonProperty
  private final int roomId;

  @Getter
  @JsonProperty
  @NotEmpty
  private final String name;

  @Getter
  @JsonProperty
  private final Timestamp created;

  @Getter
  @JsonProperty
  private final Timestamp modified;

  public Sensor(int id, int roomId, String name, Timestamp created, Timestamp modified){
    this.id = id;
    this.roomId = roomId;
    this.name = name;
    this.created = created;
    this.modified = modified;
  }

  public Sensor() {
    this.id = 0;
    this.roomId = 0;
    this.name = null;
    this.created = null;
    this.modified = null;
  }


  @Override
  public String toString() {
    return String.format("id: %s, name: %s, created: %s, modified: %s", id, name, created, modified);
  }
}
