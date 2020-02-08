package com.temperature.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.temperature.api.Room;
import com.temperature.db.RoomDao;
import com.temperature.view.RoomView;
import com.temperature.view.RoomsView;
import com.temperature.core.RoomDo;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {
  private final RoomDao roomDao;

  @Inject
  public RoomResource(RoomDao roomDao){
    this.roomDao = roomDao;
  }

  @POST
  public Response createRoom(@Valid Room room) {
    roomDao.create(room.getName());
    return Response.status(Status.CREATED).build();
  }

  @GET
  @Path("/{id}")
  @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
  public RoomView getRoom(@PathParam("id") int id) {
    return new RoomView(roomDao.findById(id));
  }

  @GET
  @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
  public RoomsView listRooms() {
    List<RoomDo> roomDoList = roomDao.findAll();
    return new RoomsView(roomDoList);
    // List<Room> rooms = new ArrayList<Room>();
    // for (RoomDo roomDo : roomDoList) {
    //   rooms.add(new Room(roomDo.getId(), roomDo.getName(), roomDo.getCreated(), roomDo.getModified()));
    // }
    // return rooms;
  }

}
