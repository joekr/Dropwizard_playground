package com.temperature.core;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

public class RoomDo {

  @Getter
  @Setter
  private int id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private Timestamp created;

  @Getter
  @Setter
  private Timestamp modified;

  public RoomDo(){
    super();
  }

  public RoomDo(int id, String name, Timestamp created, Timestamp modified) {
    this.id = id;
    this.name = name;
    this.created = created;
    this.modified = modified;
  }

}
