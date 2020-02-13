package com.temperature.resources;

import com.temperature.db.ReadingDao;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.temperature.core.ReadingDo;

import com.temperature.api.Reading;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@Produces(MediaType.APPLICATION_JSON)
public class SensorReadingResource {
  private final ReadingDao readingDao;
  private final int sensorId;

  public SensorReadingResource(int sensorId, ReadingDao readingDao) {
    this.sensorId = sensorId;
    this.readingDao = readingDao;
  }

  @POST
  @Path("")
  public Response createReading(@Valid Reading reading){
    ReadingDo readingDo = readingDao.create(this.sensorId, reading.getTemperature());
    return Response.status(Status.CREATED).entity(readingDo).build();
  }

  @GET
  @Path("")
  public List<ReadingDo> getReadings() {
    return readingDao.findAllForSensor(this.sensorId);
  }

}
