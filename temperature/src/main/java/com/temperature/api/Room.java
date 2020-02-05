package com.temperature.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

public class Room {
  @Getter
  @JsonProperty
  private final int id;

  @Getter
  @JsonProperty
  private final String name;

  public Room(int id, String name){
    this.id = id;
    this.name = name;
  }

  public Room() {
    this.id = 0;
    this.name = null;
  }
}
