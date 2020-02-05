package com.temperature.db;

import java.util.List;

import com.temperature.core.RoomDo;
import com.temperature.db.mapper.RoomMapper;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface RoomDao {
  @SqlUpdate("INSERT INTO rooms(name, created, modified) VALUES (:name, :now, :now)")
  @Timestamped
  public int create(@Bind("name") String name);

  @SqlQuery("select * from rooms where id = :id")
  @RegisterRowMapper(RoomMapper.class)
  public RoomDo findById(@Bind("id") int id);

  @SqlQuery("select * from rooms")
  @RegisterRowMapper(RoomMapper.class)
  public List<RoomDo> findAll();
}
