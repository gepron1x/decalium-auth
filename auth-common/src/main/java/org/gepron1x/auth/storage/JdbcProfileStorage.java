package org.gepron1x.auth.storage;

import org.gepron1x.auth.AuthProfile;
import org.gepron1x.auth.storage.mapper.Mappers;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.PreparedBatch;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JdbcProfileStorage implements ProfileStorage {

    private static final String UUID = "uuid", PASSWORD = "password", ADDRESS = "address", TIMESTAMP = "timestamp";
    private static final RowMapper<AuthProfile.Builder> PROFILE_MAPPER = (rs, ctx) ->
            AuthProfile.builder()
                    .uuid(Mappers.UUID.map(rs, UUID, ctx))
                    .password(rs.getString(PASSWORD));

    private final Jdbi jdbi;

    public JdbcProfileStorage(Jdbi jdbi) {
        this.jdbi = jdbi;
    }
    @Override
    public void initialize() {
        jdbi.useHandle(handle -> {
            handle.createBatch()
                    .add("CREATE TABLE IF NOT EXISTS profiles (`uuid` BINARY(16) UNIQUE NOT NULL, `password` TINYTEXT NOT NULL, PRIMARY KEY(`uuid`))")
                    .add("CREATE TABLE IF NOT EXISTS logged_addresses (`uuid` BINARY(16) UNIQUE NOT NULL, `address` VARBINARY(16) UNIQUE NOT NULL, `timestamp` TIMESTAMP NOT NULL")
                    .execute();
        });
    }

    @Override
    public AuthProfile loadProfile(UUID uuid) {



        return jdbi.withHandle(handle -> {
            AuthProfile.Builder builder = handle.createQuery("SELECT * FROM profiles WHERE `uuid`=:uuid")
                    .bind(UUID, uuid).map(PROFILE_MAPPER).first();
            Map<InetAddress, Instant> loggedIn = handle.createQuery("SELECT * FROM logged_addresses WHERE `uuid`=:uuid")
                    .bind(UUID, uuid)
                    .map((rs, ctx) -> Map.entry(Mappers.INET_ADDRESS.map(rs, ADDRESS, ctx), Mappers.INSTANT.map(rs, TIMESTAMP, ctx)))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            return builder.loggedIn(loggedIn).build();
        });
    }

    @Override
    public AuthProfile loadProfile(String username) {
        return null;
    }

    @Override
    public List<AuthProfile> loadProfiles() {
        return jdbi.withHandle(handle -> {
            List<LoggedInAddressesRow> rows = handle.createQuery("SELECT * FROM logged_addresses")
                    .map((rs, ctx) -> new LoggedInAddressesRow(
                            Mappers.UUID.map(rs, UUID, ctx),
                            Mappers.INET_ADDRESS.map(rs, ADDRESS, ctx),
                            Mappers.INSTANT.map(rs, TIMESTAMP, ctx)
                            )
                    ).collect(Collectors.toList());
            Map<UUID, AuthProfile.Builder> map = handle.createQuery("SELECT * FROM profiles")
                    .map(PROFILE_MAPPER)
                    .collect(Collectors.toMap(AuthProfile.Builder::uuid, Function.identity()));
            for(LoggedInAddressesRow row : rows) {
                map.get(row.uuid).addLoggedInAddress(row.address, row.instant);
            }
            return map.values().stream().map(AuthProfile.Builder::build).collect(Collectors.toList());
        });
    }

    @Override
    public void saveProfile(AuthProfile profile) {
        final UUID uuid = profile.getUuid();
        jdbi.useHandle(handle -> {
            handle.createUpdate("INSERT INTO `profiles` (`uuid`, `password`) VALUES (:uuid, :password) ON DUPLICATE KEY UPDATE `password`=:password")
                    .bind(UUID, uuid)
                    .bind(PASSWORD, profile.getHashedPassword())
                    .execute();
            PreparedBatch batch = handle.prepareBatch("INSERT INTO `logged_addresses` (`uuid`, `address`, `timestamp`) VALUES(:uuid, :address, :timestamp) ON DUPLICATE KEY UPDATE `timestamp`=:timestamp");
            profile.getLoggedInAddresses().forEach(
                    (key, value) -> batch
                            .bind(UUID, uuid)
                            .bind(ADDRESS, key)
                            .bind(TIMESTAMP, value)
                            .add()
            );
            batch.execute();
        });

    }

    @Override
    public void setPassword(AuthProfile profile, String hashedPassword) {
        jdbi.useHandle(handle -> {
            handle.createUpdate("UPDATE profiles SET `password`=:password WHERE `uuid`=:uuid")
                    .bind(UUID, profile.getUuid())
                    .bind(PASSWORD, hashedPassword)
                    .execute();
        });
    }

    @Override
    public void addLoggedInAddress(AuthProfile profile, InetAddress address, Instant instant) {
        jdbi.useHandle(handle -> {
            handle.createUpdate("INSERT INTO logged_addresses (`uuid`, `address`, `timestamp` VALUES(:uuid, :address, :timestamp) ON DUPLICATE KEY UPDATE `timestamp`=:timestamp")
                    .bind(UUID, profile.getUuid())
                    .bind(ADDRESS, address)
                    .bind(TIMESTAMP, instant)
                    .execute();
        });
    }

    @Override
    public void removeLoggedInAddress(AuthProfile profile, InetAddress address) {
        jdbi.useHandle(handle -> {
            handle.createUpdate("DELETE FROM logged_addresses WHERE `uuid`=:uuid AND `address`=:address")
                    .bind(UUID, profile.getUuid())
                    .bind(ADDRESS, address).execute();
        });
    }


    private static class LoggedInAddressesRow {
        final UUID uuid;
        final InetAddress address;
        final Instant instant;

        private LoggedInAddressesRow(UUID uuid, InetAddress address, Instant instant) {
            this.uuid = uuid;
            this.address = address;
            this.instant = instant;
        }
    }






}
