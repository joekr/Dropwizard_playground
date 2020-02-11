package com.temperature.core;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

public class ReadingDo {

  @Getter
  @Setter
  private int id;

  @Getter
  @Setter
  private int sensorId;

  @Getter
  @Setter
  private float temperature;

  @Getter
  @Setter
  private Timestamp created;

  @Getter
  @Setter
  private Timestamp modified;

  public ReadingDo(int id, int sensorId, float temperature, Timestamp created, Timestamp modified) {
    this.id = id;
    this.sensorId = sensorId;
    this.temperature = temperature;
    this.created = created;
    this.modified = modified;
  }

}
