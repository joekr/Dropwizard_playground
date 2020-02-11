package com.temperature.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.temperature.core.ReadingDo;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class ReadingMapper implements RowMapper<ReadingDo> {
  private static final String id = "id";
  private static final String sensorId = "sensor_id";
  private static final String temperature = "temperature";
  private static final String created = "created";
  private static final String modified = "modified";

  @Override
  public ReadingDo map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new ReadingDo(rs.getInt(id),
      rs.getInt(sensorId),
      rs.getFloat(temperature),
      rs.getTimestamp(created),
      rs.getTimestamp(modified));
  }

}
