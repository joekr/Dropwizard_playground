package com.temperature.view;

import io.dropwizard.views.View;
import lombok.Getter;

import java.util.List;

import com.temperature.core.RoomDo;

public class RoomsView extends View {
  @Getter
  private final List<RoomDo> rooms;

  public RoomsView(List<RoomDo> rooms) {
    super("rooms.ftl");
    this.rooms = rooms;
  }

}
