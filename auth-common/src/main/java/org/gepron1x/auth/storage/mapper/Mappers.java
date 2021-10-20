package org.gepron1x.auth.storage.mapper;

public final class Mappers {
    private Mappers() {
        throw new UnsupportedOperationException();
    }

    public static final UuidMapper UUID = new UuidMapper();
    public static final InetAddressMapper INET_ADDRESS = new InetAddressMapper();
    public static final InstantMapper INSTANT = new InstantMapper();
}
