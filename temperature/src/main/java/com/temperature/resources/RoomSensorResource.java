package com.temperature.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.temperature.api.Sensor;
import com.temperature.core.SensorDo;
import com.temperature.db.SensorDao;

public class RoomSensorResource {
  private final SensorDao sensorDao;
  private final int roomId;

  public RoomSensorResource(int roomId, SensorDao sensorDao){
    this.sensorDao = sensorDao;
    this.roomId = roomId;
  }

  @GET
  @Path("/sensors")
  public List<SensorDo> getSensors() {
    return sensorDao.findAll(this.roomId);
  }

}
