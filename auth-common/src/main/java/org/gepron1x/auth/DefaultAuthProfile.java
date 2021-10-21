package org.gepron1x.auth;

import me.gepron1x.auth.api.AuthProfile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.net.InetAddress;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DefaultAuthProfile implements AuthProfile {

    private final UUID uuid;
    private String hashedPassword;
    private final Map<InetAddress, Instant> loggedIn;

    public DefaultAuthProfile(UUID uuid, String hashedPassword, Map<InetAddress, Instant> loggedIn) {

        this.uuid = uuid;
        this.hashedPassword = hashedPassword;
        this.loggedIn = loggedIn;
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return uuid;
    }

    @Override
    public @NotNull String getHashedPassword() {
        return hashedPassword;
    }

    @Override
    public void setHashedPassword(@NotNull String password) {
        this.hashedPassword = password;
    }

    @Override
    public @Nullable Instant getLastLogin(InetAddress address) {
        return loggedIn.get(address);
    }

    @Override
    public void logIn(@NotNull InetAddress address, @NotNull Instant instant) {
        loggedIn.put(address, instant);
    }

    @Override
    public @NotNull @UnmodifiableView Map<InetAddress, Instant> getLoggedInAddresses() {
        return Collections.unmodifiableMap(loggedIn);
    }

    @Override
    public @NotNull AuthProfile.Builder toBuilder() {
        return new Builder().uuid(this.uuid).password(this.hashedPassword).loggedIn(this.loggedIn);
    }

    public static Builder builder() {
        return new Builder();
    }

    static class Builder implements AuthProfile.Builder {
        private UUID uuid;
        private String hashedPassword;
        private final Map<InetAddress, Instant> loggedIn = new HashMap<>();

        @Override
        public AuthProfile.Builder uuid(@NotNull UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        @Override
        public AuthProfile.Builder password(@NotNull String password) {
            this.hashedPassword = password;
            return this;
        }

        @Override
        public AuthProfile.Builder addLoggedInAddress(InetAddress address, @NotNull Instant instant) {
            this.loggedIn.put(address, instant);
            return this;
        }

        @Override
        public AuthProfile.Builder loggedIn(@NotNull Map<InetAddress, Instant> loggedIn) {
            this.loggedIn.clear();
            this.loggedIn.putAll(loggedIn);
            return this;
        }

        @Override
        public AuthProfile.Builder clearLoggedIn() {
            this.loggedIn.clear();
            return this;
        }

        @Override
        public @Nullable UUID uuid() {
            return this.uuid;
        }

        @Override
        public @Nullable String password() {
            return this.hashedPassword;
        }

        @Override
        public @NotNull Map<InetAddress, Instant> loggedIn() {
            return Collections.unmodifiableMap(this.loggedIn);
        }

        @Override
        public @NotNull AuthProfile build() {
            return new DefaultAuthProfile(uuid, hashedPassword, loggedIn);
        }
    }
}
