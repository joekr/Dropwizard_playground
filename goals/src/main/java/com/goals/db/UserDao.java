package com.goals.db;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.goals.core.GoalDo;
import com.goals.core.UserDo;
import com.goals.db.mapper.GoalMapper;
import com.goals.db.mapper.UserMapper;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

public interface UserDao {

  @SqlQuery("INSERT into users(email, password_hash, team_id, confirmation_token, created_at, modified_at) VALUES(:username, :password, :teamId, :token, :now, :now) returning *")
  @RegisterRowMapper(UserMapper.class)
  @Timestamped
  @GetGeneratedKeys
  public Optional<UserDo> create(@Bind("username") String username, @Bind("password") String password, @Bind("teamId") int teamId, @Bind("token") String token);

  @SqlQuery("SELECT * from users where email = :username and confirmed_at is not null")
  @RegisterRowMapper(UserMapper.class)
  @Timestamped
  public Optional<UserDo> findByEmail(@Bind("username") String username);

  @SqlQuery("SELECT * from users where confirmation_token = :token and confirmed_at is null")
  @RegisterRowMapper(UserMapper.class)
  @Timestamped
  public Optional<UserDo> findByConfermationToke(@Bind("token") String token);

  @SqlUpdate("UPDATE users set confirmation_token = null, confirmed_at = :now where id = :id and confirmed_at is null")
  @Timestamped
  public int confirm(@Bind("id") int id);
}
