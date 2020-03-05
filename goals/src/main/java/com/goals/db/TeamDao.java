package com.goals.db;

import java.util.List;
import java.util.Optional;


import com.goals.core.GoalDo;
import com.goals.core.TeamDo;
import com.goals.core.UserDo;
import com.goals.db.mapper.GoalMapper;
import com.goals.db.mapper.TeamMapper;
import com.goals.db.mapper.UserMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

public interface TeamDao {

    @SqlQuery("INSERT into teams(name, created_at, modified_at) VALUES(:name, :now, :now) returning *")
    @RegisterRowMapper(TeamMapper.class)
    @Timestamped
    public Optional<TeamDo> create(@Bind("name") String name);

    @SqlQuery("SELECT * from teams where id = :id")
    @RegisterRowMapper(TeamMapper.class)
    @Timestamped
    public Optional<TeamDo> findById(@Bind("id") int id);

    @SqlQuery("SELECT * from users where team_id = :teamId")
    @RegisterRowMapper(UserMapper.class)
    @Timestamped
    public List<UserDo> findAllUsersByTeamId(@Bind("teamId") int teamId);

    @SqlQuery("SELECT * FROM goals WHERE user_id IN (SELECT id FROM users WHERE team_id = :teamId) order by created_at asc")
    @RegisterRowMapper(GoalMapper.class)
    @Timestamped
    public List<GoalDo> findAllGoalsByTeamId(@Bind("teamId") int teamId);

}
