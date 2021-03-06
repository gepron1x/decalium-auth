package org.gepron1x.auth.storage.argument;

import org.gepron1x.auth.util.UuidUtil;
import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;

import java.sql.Types;
import java.util.UUID;

public class UuidArgumentFactory extends AbstractArgumentFactory<UUID> {

    public UuidArgumentFactory() {
        super(Types.BINARY);
    }

    @Override
    protected Argument build(UUID value, ConfigRegistry config) {
        return (position, statement, ctx) -> statement.setBytes(position, UuidUtil.toByteArray(value));
    }
}
