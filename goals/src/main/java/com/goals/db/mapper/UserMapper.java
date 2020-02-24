package com.goals.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.goals.core.UserDo;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class UserMapper implements RowMapper<UserDo> {
  private static final String id = "id";
  private static final String email = "email";
  private static final String passwordHash = "password_hash";
  private static final String teamId = "team_id";
  private static final String confirmationToken = "confirmation_token";
  private static final String confirmedAt = "confirmed_at";
  private static final String lastLoginAt = "last_login_at";
  private static final String createdAt = "created_at";
  private static final String modifiedAt = "modified_at";

  @Override
  public UserDo map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new UserDo(rs.getInt(id),
      rs.getString(email),
      rs.getString(passwordHash),
      rs.getInt(teamId),
      rs.getString(confirmationToken),
      rs.getTimestamp(confirmedAt),
      rs.getTimestamp(lastLoginAt),
      rs.getTimestamp(createdAt),
      rs.getTimestamp(modifiedAt));
  }

}
