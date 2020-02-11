package com.temperature.resources;

import com.temperature.db.ReadingDao;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.temperature.core.ReadingDo;

@Produces(MediaType.APPLICATION_JSON)
public class SensorReadingResource {
  private final ReadingDao readingDao;
  private final int sensorId;

  public SensorReadingResource(int sensorId, ReadingDao readingDao) {
    this.sensorId = sensorId;
    this.readingDao = readingDao;
  }

  @GET
  @Path("")
  public List<ReadingDo> getReadings() {
    return readingDao.findAllForSensor(this.sensorId);
  }

}
