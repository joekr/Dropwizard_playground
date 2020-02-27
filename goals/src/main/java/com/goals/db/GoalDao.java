package com.goals.db;

import java.util.List;
import java.util.Optional;


import com.goals.api.Goal;
import com.goals.core.GoalDo;
import com.goals.db.mapper.GoalMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Timestamped;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

public interface GoalDao {

    @SqlQuery("INSERT into goals(user_id, name, goal, created_at, modified_at) VALUES(:userId, :g.name, :g.goal, :now, :now) returning *")
    @RegisterRowMapper(GoalMapper.class)
    @Timestamped
    public Optional<GoalDo> create(@BindBean("g") Goal goal, @Bind("userId") int userId);

    @SqlQuery("SELECT * from goals where user_id = :userId")
    @RegisterRowMapper(GoalMapper.class)
    @Timestamped
    public List<GoalDo> findById(@Bind("userId") int userId);

}
