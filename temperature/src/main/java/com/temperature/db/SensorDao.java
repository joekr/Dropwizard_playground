package com.temperature.db;

import com.temperature.db.mapper.SensorMapper;
import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import com.temperature.core.SensorDo;

public interface SensorDao {

  @SqlQuery("INSERT INTO sensors(room_id, name, created, modified) VALUES (:roomId, :name, :now, :now) returning *")
  @RegisterRowMapper(SensorMapper.class)
  @Timestamped
  public SensorDo create(@Bind("roomId") int roomId, @Bind("name") String name);

  @SqlQuery("select * from sensors where room_id = :roomId")
  @RegisterRowMapper(SensorMapper.class)
  public List<SensorDo> findAll(@Bind("roomId") int roomId);

  @SqlQuery("select * from sensors where id = :id")
  @RegisterRowMapper(SensorMapper.class)
  public SensorDo findById(@Bind("id") int id);

  @SqlQuery("UPDATE sensors SET name = :name, modified = :now where id = :id returning *")
  @RegisterRowMapper(SensorMapper.class)
  @Timestamped
  public SensorDo update(@Bind("id") int id, @Bind("name") String name);

  @SqlUpdate("delete from sensors where room_id = :roomId and id = :id")
  @Timestamped
  public int delete(@Bind("roomId") int roomId, @Bind("id") int id);

}
