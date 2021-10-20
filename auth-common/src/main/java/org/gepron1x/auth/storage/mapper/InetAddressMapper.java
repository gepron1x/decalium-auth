package org.gepron1x.auth.storage.mapper;

import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InetAddressMapper implements ColumnMapper<InetAddress> {
    @Override
    public InetAddress map(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException {

        try {
            return InetAddress.getByAddress(r.getBytes(columnNumber));
        } catch (UnknownHostException e) {
            throw new SQLException(e);
        }
    }
}
