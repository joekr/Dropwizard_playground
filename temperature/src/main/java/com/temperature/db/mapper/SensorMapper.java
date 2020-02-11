package com.temperature.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.temperature.core.SensorDo;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class SensorMapper implements RowMapper<SensorDo> {
  private static final String id = "id";
  private static final String roomId = "room_id";
  private static final String name = "name";
  private static final String created = "created";
  private static final String modified = "modified";

  @Override
  public SensorDo map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new SensorDo(rs.getInt(id),
      rs.getInt(roomId),
      rs.getString(name),
      rs.getTimestamp(created),
      rs.getTimestamp(modified));
  }

}
