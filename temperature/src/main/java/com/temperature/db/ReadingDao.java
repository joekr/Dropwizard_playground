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

  @SqlQuery("select * from readings where sensor_id = :sensorId")
  @RegisterRowMapper(ReadingMapper.class)
  public List<ReadingDo> findAllForSensor(@Bind("sensorId") int sensorId);

}
