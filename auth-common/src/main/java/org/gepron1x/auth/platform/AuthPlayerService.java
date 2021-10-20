package org.gepron1x.auth.platform;

import org.gepron1x.auth.AuthProfile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

public interface AuthPlayerService {
    @NotNull Collection<AuthPlayer> getOnlinePlayers();
    @Nullable AuthPlayer getByName(@NotNull String name);
    @Nullable AuthPlayer getByUuid(@NotNull UUID uuid);
    @Nullable default AuthPlayer getByProfile(@NotNull AuthProfile profile) {
        return getByUuid(profile.getUuid());
    }
}
