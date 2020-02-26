package com.goals.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.goals.core.TeamDo;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class TeamMapper implements RowMapper<TeamDo> {
    private static final String id = "id";
    private static final String name = "name";
    private static final String createdAt = "created_at";
    private static final String modifiedAt = "modified_at";

    @Override
    public TeamDo map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new TeamDo(rs.getInt(id),
                rs.getString(name),
                rs.getTimestamp(createdAt),
                rs.getTimestamp(modifiedAt));
    }

}
