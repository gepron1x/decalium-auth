package me.gepron1x.auth.api;

import net.kyori.adventure.util.Buildable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.net.InetAddress;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public interface AuthProfile extends Buildable<AuthProfile, AuthProfile.Builder> {
    @NotNull
    UUID getUniqueId();

    @NotNull
    String getHashedPassword();

    void setHashedPassword(@NotNull String password);

    @Nullable
    Instant getLastLogin(InetAddress address);

    void logIn(@NotNull InetAddress address, @NotNull Instant instant);

    default void logIn(@NotNull InetAddress address) {
        logIn(address, Instant.now());
    }

    @NotNull
    @UnmodifiableView
    Map<InetAddress, Instant> getLoggedInAddresses();

    interface Builder extends Buildable.Builder<AuthProfile> {
        Builder uuid(@NotNull UUID uuid);

        Builder password(@NotNull String password);

        Builder addLoggedInAddress(InetAddress address, @NotNull Instant instant);

        Builder loggedIn(@NotNull Map<InetAddress, Instant> loggedIn);

        Builder clearLoggedIn();

        @Nullable UUID uuid();

        @Nullable String password();

        @NotNull Map<InetAddress, Instant> loggedIn();
    }


}
