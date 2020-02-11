package com.temperature.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.temperature.core.SensorDo;
import com.temperature.core.ReadingDo;
import com.temperature.db.ReadingDao;
import com.temperature.db.SensorDao;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
public class SensorResource {
  private final SensorDao sensorDao;
  private final ReadingDao readingDao;

  // private static final Logger log = LoggerFactory.getLogger(RoomResource.class);

  public SensorResource(SensorDao sensorDao, ReadingDao readingDao){
    this.sensorDao = sensorDao;
    this.readingDao = readingDao;
  }

  @GET
  @Path("/{id}")
  public SensorDo getSensor(@PathParam("id") int id) {
    return sensorDao.findById(id);
  }

  @Path("/{id}/readings")
  public SensorReadingResource getSensorReadings(@PathParam("id") int id) {
    return new SensorReadingResource(id, this.readingDao);
  }

}
