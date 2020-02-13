package com.temperature.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.temperature.api.Sensor;
import com.temperature.core.SensorDo;
import com.temperature.db.SensorDao;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Produces(MediaType.APPLICATION_JSON)
public class RoomSensorResource {
  private final SensorDao sensorDao;
  private final int roomId;

  private static final Logger log = LoggerFactory.getLogger(RoomSensorResource.class);

  public RoomSensorResource(int roomId, SensorDao sensorDao){
    this.sensorDao = sensorDao;
    this.roomId = roomId;
  }

  @POST
  @Path("/sensors")
  public Response createSensor(@Valid Sensor sensor) {
    log.info("sensor = {}", sensor);
    SensorDo sensorDo = sensorDao.create(this.roomId, sensor.getName());
    return Response.status(Status.CREATED).entity(sensorDo).build();
  }

  @GET
  @Path("/sensors")
  public List<SensorDo> getSensors() {
    return sensorDao.findAll(this.roomId);
  }

  @PUT
  @Path("/sensors/{id}")
  public Response updateSensor(@PathParam("id") int id, @Valid Sensor sensor) {
    log.info("sensor = {}", sensor);
    SensorDo sensorDo = sensorDao.update(id, sensor.getName());
    log.info("SensorDo = {}", sensorDo);
    if (sensorDo != null) {
      return Response.status(Status.OK).entity(sensorDo).build();
    } else {
      return Response.status(Status.NOT_FOUND).entity("{'message': 'Sensor not found'}").build();
    }
  }

  // maybe move this to SensorResource but who knows.
  @DELETE
  @Path("/sensors/{id}")
  public Response deleteSensor(@PathParam("id") int id) {
    int deleteCount = sensorDao.delete(this.roomId, id);
    if (deleteCount > 0) {
      return Response.status(Status.OK).build();
    } else {
      return Response.status(Status.NOT_FOUND).entity("{'message': 'Sensor not found'}").build();
    }
  }

}
