package com.temperature.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.temperature.api.Room;
import com.temperature.db.RoomDao;
import com.temperature.db.SensorDao;
import com.temperature.view.CreateRoomView;
import com.temperature.view.RoomView;
import com.temperature.view.RoomsView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.temperature.core.RoomDo;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {
  private final RoomDao roomDao;
  private final SensorDao sensorDao;

  private static final Logger log = LoggerFactory.getLogger(RoomResource.class);

  public RoomResource(RoomDao roomDao, SensorDao sensorDao){
    this.roomDao = roomDao;
    this.sensorDao = sensorDao;
  }

  @GET
  @Path("/new")
  @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
  public CreateRoomView newRoom() {
    return new CreateRoomView();
  }

  @POST
  public Response createRoom(@Valid Room room) {
    log.info("createRoom - room: {}", room);
    roomDao.create(room.getName());
    return Response.status(Status.CREATED).build();
  }

  @GET
  @Path("/{id}")
  @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
  public RoomView getRoom(@PathParam("id") int id) {
    log.info("getRoom - ID: {}", id);
    return new RoomView(roomDao.findById(id));
  }

  @GET
  @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
  public RoomsView listRooms() {
    List<RoomDo> roomDoList = roomDao.findAll();
    return new RoomsView(roomDoList);
  }

  @PUT
  @Path("/{id}")
  public int getRoom(@PathParam("id") int id, @Valid Room room) {
    return roomDao.update(id, room.getName());
  }

  @DELETE
  @Path("/{id}")
  public Response deleteRoom(@PathParam("id") int id) {
    log.info("deleteRoom - id: {}", id);

    int deleteCount = roomDao.delete(id);
    if (deleteCount > 0) {
      return Response.status(Status.OK).build();
    } else {
      return Response.status(Status.NOT_FOUND).entity("{'message': 'Room not found'}").build();
    }
  }

  @Path("/{id}")
  public RoomSensorResource getSensors(@PathParam("id") int id) {
      return new RoomSensorResource(id, this.sensorDao);
  }

}
