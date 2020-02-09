package com.temperature.api;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.validation.ValidationMethod;

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
  private final Timestamp created;

  @Getter
  @JsonProperty
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

  @ValidationMethod(message = "Room has a known bad name. Please try another one.")
  public boolean isBadName() {
    if (this.name.toLowerCase() == "unknown") {
      return false;
    }else{
      return true;
    }
  }

  @Override
  public String toString() {
    return String.format("id: %s, name: %s, created: %s, modified: %s", id, name, created, modified);
  }
}
