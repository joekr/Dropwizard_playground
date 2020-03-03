package com.goals.db;

import java.sql.Timestamp;
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

import javax.swing.text.html.Option;

public interface GoalDao {

    @SqlQuery("INSERT into goals(user_id, name, goal, created_at, modified_at) VALUES(:userId, :g.name, :g.goal, :now, :now) returning *")
    @RegisterRowMapper(GoalMapper.class)
    @Timestamped
    public Optional<GoalDo> create(@BindBean("g") Goal goal, @Bind("userId") int userId);

    @SqlQuery("UPDATE goals set user_id = :userId, name = :name, goal = :goal, completed_at = :completed_at, modified_at = :now where id = :id returning *")
    @RegisterRowMapper(GoalMapper.class)
    @Timestamped
    public Optional<GoalDo> update(@Bind("id") int id, @Bind("userId") int userId, @Bind("name") String name, @Bind("goal") String goal, @Bind("completed_at") Timestamp completedAt);

    @SqlQuery("SELECT * from goals where user_id = :userId")
    @RegisterRowMapper(GoalMapper.class)
    @Timestamped
    public List<GoalDo> findByUserId(@Bind("userId") int userId);

    @SqlQuery("SELECT * from goals where id = :id and user_id = :userId")
    @RegisterRowMapper(GoalMapper.class)
    @Timestamped
    public Optional<GoalDo> findById(@Bind("id") int id, @Bind("userId") int userId);

    @SqlUpdate("delete from goals where user_id = :userId and id = :id")
    @Timestamped
    public int delete(@Bind("userId") int userId, @Bind("id") int id);
}
