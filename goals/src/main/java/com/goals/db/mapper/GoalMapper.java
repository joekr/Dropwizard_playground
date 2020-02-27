package com.goals.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.goals.core.GoalDo;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class GoalMapper implements RowMapper<GoalDo> {
    private static final String id = "id";
    private static final String userId = "user_id";
    private static final String name = "name";
    private static final String goal = "goal";
    private static final String completedAt = "completed_at";
    private static final String createdAt = "created_at";
    private static final String modifiedAt = "modified_at";

    @Override
    public GoalDo map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new GoalDo(rs.getInt(id),
                rs.getInt(userId),
                rs.getString(name),
                rs.getString(goal),
                rs.getTimestamp(completedAt),
                rs.getTimestamp(createdAt),
                rs.getTimestamp(modifiedAt));
    }

}
