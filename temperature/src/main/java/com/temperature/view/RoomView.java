package com.temperature.view;

import io.dropwizard.views.View;
import lombok.Getter;

import com.temperature.core.RoomDo;

public class RoomView extends View {
  @Getter
  private final RoomDo room;

  public RoomView(RoomDo room) {
    super("room.ftl");
    this.room = room;
  }

}
