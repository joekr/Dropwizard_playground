package com.goals.db;

import java.util.List;

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

  @SqlQuery("INSERT into users(email, password_hash, team_id, created_at, modified_at) VALUES(:username, :password, 2, :now, :now) returning *")
  @RegisterRowMapper(UserMapper.class)
  @Timestamped
  @GetGeneratedKeys
  public UserDo create(@Bind("username") String username, @Bind("password") String password);

  @SqlQuery("SELECT * from users where email = :username and confirmed_at is not null")
  @RegisterRowMapper(UserMapper.class)
  @Timestamped
//  TODO: optional
  public UserDo findByEmail(@Bind("username") String username);

}
