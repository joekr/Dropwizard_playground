package com.goals.db;

import java.util.List;
import java.util.Optional;

import com.goals.core.UserDo;
import com.goals.db.mapper.UserMapper;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

public interface UserDao {

  @SqlQuery("INSERT into users(email, password_hash, team_id, created_at, modified_at) VALUES(:username, :password, :teamId, :now, :now) returning *")
  @RegisterRowMapper(UserMapper.class)
  @Timestamped
  @GetGeneratedKeys
  public Optional<UserDo> create(@Bind("username") String username, @Bind("password") String password, @Bind("teamId") int teamId);

  @SqlQuery("SELECT * from users where email = :username and confirmed_at is not null")
  @RegisterRowMapper(UserMapper.class)
  @Timestamped
//  TODO: optional
  public Optional<UserDo> findByEmail(@Bind("username") String username);

}
