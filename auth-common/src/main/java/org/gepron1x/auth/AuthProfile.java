package org.gepron1x.auth;

import net.kyori.adventure.util.Buildable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.net.InetAddress;
import java.time.Instant;
import java.util.*;

public class AuthProfile implements Buildable<AuthProfile, AuthProfile.Builder> {
    private final UUID uuid;
    private final Map<InetAddress, Instant> loggedIn;
    private String hashedPassword;


    public AuthProfile(@NotNull UUID uuid, @NotNull String hashedPassword, @NotNull Map<InetAddress, Instant> loggedIn) {
        this.uuid = uuid;
        this.hashedPassword = hashedPassword;
        this.loggedIn = loggedIn;
    }


    @Nullable
    public Instant getLoginTimeStamp(@NotNull InetAddress address) {
        return loggedIn.get(address);
    }

    @NotNull
    @UnmodifiableView
    public Map<InetAddress, Instant> getLoggedInAddresses() {
        return Collections.unmodifiableMap(loggedIn);
    }

    public void logIn(InetAddress address) {
        loggedIn.put(address, Instant.now());
    }
    public String getHashedPassword() {
        return hashedPassword;
    }


    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }


    public UUID getUuid() {
        return uuid;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public @NotNull AuthProfile.Builder toBuilder() {
        return builder().uuid(uuid).password(hashedPassword).loggedIn(loggedIn);
    }

    public static class Builder implements Buildable.Builder<AuthProfile> {
        private UUID uuid;
        private String hashedPassword;
        private final Map<InetAddress, Instant> loggedIn = new HashMap<>();
        private Builder() {

        }
        public Builder uuid(@NotNull UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder password(@NotNull String hashedPassword) {
            this.hashedPassword = hashedPassword;
            return this;
        }
        public Builder addLoggedInAddress(@NotNull InetAddress address, Instant timestamp) {
            loggedIn.put(address, timestamp);
            return this;
        }
        public Builder loggedIn(@NotNull Map<InetAddress, Instant> loggedIn) {
            this.loggedIn.clear();
            this.loggedIn.putAll(loggedIn);
            return this;
        }

        public Builder clearLoggedIn() {
            this.loggedIn.clear();
            return this;
        }
        @Nullable
        public UUID uuid() {
            return this.uuid;
        }
        @Nullable
        public String password() {
            return this.hashedPassword;
        }
        @NotNull
        @UnmodifiableView
        public Map<InetAddress, Instant> loggedIn() {
            return Collections.unmodifiableMap(loggedIn);
        }

        @Override
        public @NotNull AuthProfile build() {
            return new AuthProfile(
                    Objects.requireNonNull(uuid, "uuid"),
                    Objects.requireNonNull(hashedPassword, "password"),
                    loggedIn
            );
        }
    }
}
