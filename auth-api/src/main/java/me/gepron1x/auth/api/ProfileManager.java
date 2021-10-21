package me.gepron1x.auth.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.function.Consumer;

public interface ProfileManager {
    @Nullable AuthProfile getProfile(@NotNull UUID uniqueId);

    @NotNull AuthProfile.Builder profileBuilder();

    void addProfile(@NotNull AuthProfile profile);

    void removeProfile(@NotNull AuthProfile profile);

    default void buildAndAdd(Consumer<AuthProfile.Builder> configure) {
        AuthProfile.Builder builder = profileBuilder();
        configure.accept(builder);
        addProfile(builder.build());
    }

}
