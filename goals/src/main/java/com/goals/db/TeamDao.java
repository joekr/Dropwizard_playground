package com.goals.db;

import java.util.List;
import java.util.Optional;


import com.goals.core.TeamDo;
import com.goals.db.mapper.TeamMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

public interface TeamDao {

//    @SqlQuery("INSERT into users(email, password_hash, team_id, created_at, modified_at) VALUES(:username, :password, 2, :now, :now) returning *")
//    @RegisterRowMapper(TeamMapper.class)
//    @Timestamped
//    @GetGeneratedKeys
//    public UserDo create(@Bind("username") String username, @Bind("password") String password);

    @SqlQuery("SELECT * from teams where id = :id")
    @RegisterRowMapper(TeamMapper.class)
    @Timestamped
    public Optional<TeamDo> findById(@Bind("id") int id);

}
