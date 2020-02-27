package com.goals.db;

import java.util.List;


import com.goals.core.GoalDo;
import com.goals.db.mapper.GoalMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

public interface GoalDao {

//    @SqlQuery("INSERT into teams(name, created_at, modified_at) VALUES(:name, :now, :now) returning *")
//    @RegisterRowMapper(TeamMapper.class)
//    @Timestamped
//    public Optional<TeamDo> create(@Bind("name") String name);

    @SqlQuery("SELECT * from goals where user_id = :userId")
    @RegisterRowMapper(GoalMapper.class)
    @Timestamped
    public List<GoalDo> findById(@Bind("userId") int userId);

}
