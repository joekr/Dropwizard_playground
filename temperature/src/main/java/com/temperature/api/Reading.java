package com.temperature.api;

import java.sql.Timestamp;

import lombok.Getter;

public class Reading {
  @Getter
  private int id;

  @Getter
  private int sensorId;

  @Getter
  private float temperature;

  @Getter
  private Timestamp created;

  @Getter
  private Timestamp modified;

  public Reading() {
    this.id = 0;
    this.sensorId = 0;
    this.temperature = 0;
    this.created = null;
    this.modified = null;
  }

  public Reading(int id, int sensorId, float temperature, Timestamp created, Timestamp modified) {
    this.id = 0;
    this.sensorId = 0;
    this.temperature = 0;
    this.created = null;
    this.modified = null;
  }
}
