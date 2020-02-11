package com.temperature.core;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

public class SensorDo {

  @Getter
  @Setter
  private int id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private int roomId;

  @Getter
  @Setter
  private Timestamp created;

  @Getter
  @Setter
  private Timestamp modified;

  public SensorDo(int id, int roomId, String name, Timestamp created, Timestamp modified) {
    this.id = id;
    this.roomId = roomId;
    this.name = name;
    this.created = created;
    this.modified = modified;
  }
}
