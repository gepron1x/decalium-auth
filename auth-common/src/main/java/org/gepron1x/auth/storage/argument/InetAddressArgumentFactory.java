package org.gepron1x.auth.storage.argument;

import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;

import java.net.InetAddress;
import java.sql.Types;

public class InetAddressArgumentFactory extends AbstractArgumentFactory<InetAddress> {

    public InetAddressArgumentFactory() {
        super(Types.VARBINARY);
    }

    @Override
    protected Argument build(InetAddress value, ConfigRegistry config) {
        return (position, statement, ctx) -> statement.setBytes(position, value.getAddress());
    }
}
