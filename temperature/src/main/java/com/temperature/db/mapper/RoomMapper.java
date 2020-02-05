package com.temperature.db.mapper;

import com.temperature.core.RoomDo;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<RoomDo> {
  private static final String id = "id";
  private static final String name = "name";
  private static final String created = "created";
  private static final String modified = "modified";

  public RoomMapper(){

  }

  public RoomDo map(ResultSet resultSet, StatementContext statementContext)
  throws SQLException {
    return new RoomDo(resultSet.getInt(id),
      resultSet.getString(name),
      resultSet.getTimestamp(created),
      resultSet.getTimestamp(modified));
  }

}
