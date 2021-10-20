package org.gepron1x.auth.storage.argument;

import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;

public final class InstantArgumentFactory extends AbstractArgumentFactory<Instant> {

    public InstantArgumentFactory() {
        super(Types.TIMESTAMP);
    }

    @Override
    protected Argument build(Instant value, ConfigRegistry config) {
        return ((position, statement, ctx) -> statement.setTimestamp(position, Timestamp.from(value)));
    }
}
