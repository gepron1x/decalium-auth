package org.gepron1x.auth.storage.mapper;

import org.gepron1x.auth.util.UuidUtil;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UuidMapper implements ColumnMapper<UUID> {
    @Override
    public UUID map(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException {
        return UuidUtil.fromByteArray(r.getBytes(columnNumber));
    }
}
