package com.ident.db;

import com.ident.core.UserDo;
import com.ident.db.mapper.UserMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;

public interface UserDao {

    @SqlQuery("INSERT into users(email, password_hash, confirmation_token, created_at, modified_at) VALUES(:username, :password, :token, :now, :now) returning *")
    @RegisterRowMapper(UserMapper.class)
    @Timestamped
    @GetGeneratedKeys
    public Optional<UserDo> create(@Bind("username") String username, @Bind("password") String password, @Bind("token") String token);

    @SqlQuery("SELECT * from users where confirmation_token = :token and confirmed_at is null")
    @RegisterRowMapper(UserMapper.class)
    @Timestamped
    public Optional<UserDo> findByConfermationToke(@Bind("token") String token);

    @SqlUpdate("UPDATE users set confirmation_token = null, confirmed_at = :now, modified_at = :now where id = :id and confirmed_at is null")
    @Timestamped
    public int confirm(@Bind("id") int id);

    @SqlUpdate("UPDATE users set last_login_at = :now, modified_at = :now where id = :id")
    @Timestamped
    public int updateLoggedin(@Bind("id") int id);

    @SqlQuery("SELECT * from users where email = :username and confirmed_at is not null")
    @RegisterRowMapper(UserMapper.class)
    @Timestamped
    public Optional<UserDo> findByEmail(@Bind("username") String username);
}