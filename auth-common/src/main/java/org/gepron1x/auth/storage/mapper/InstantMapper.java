package org.gepron1x.auth.storage.mapper;

import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class InstantMapper implements ColumnMapper<Instant> {
    @Override
    public Instant map(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException {
        return r.getTimestamp(columnNumber).toInstant();
    }
}
