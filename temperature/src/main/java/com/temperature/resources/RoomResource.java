package com.temperature.resources;

import javax.ws.rs.Path;
import javax.ws.rs.POST;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.temperature.api.Room;
import com.temperature.db.RoomDao;
import com.temperature.core.RoomDo;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {
  private final RoomDao roomDao;

  public RoomResource(RoomDao roomDao){
    this.roomDao = roomDao;
  }

  @POST
  public Response createRoom(Room room) {
    roomDao.create(room.getName());
    return Response.status(Status.CREATED).build();
  }

  @GET
  public List<Room> listRooms() {
    List<RoomDo> roomDoList = roomDao.findAll();
    List<Room> rooms = new ArrayList<Room>();
    for (RoomDo roomDo : roomDoList) {
      rooms.add(new Room(roomDo.getId(), roomDo.getName()));
    }
    return rooms;
  }

}
