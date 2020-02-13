package com.temperature.db;

import java.util.List;

import com.temperature.db.mapper.ReadingMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import com.temperature.core.ReadingDo;

public interface ReadingDao {

  @SqlQuery("INSERT into readings(sensor_id, temperature, created, modified) VALUES(:sensorId, :temperature, :now, :now) returning *")
  @RegisterRowMapper(ReadingMapper.class)
  @Timestamped
  public ReadingDo create(@Bind("sensorId") int sensorId, @Bind("temperature") float temperature);

  @SqlQuery("select * from readings where sensor_id = :sensorId order by created ASC")
  @RegisterRowMapper(ReadingMapper.class)
  public List<ReadingDo> findAllForSensor(@Bind("sensorId") int sensorId);

}
