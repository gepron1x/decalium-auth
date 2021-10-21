package org.gepron1x.auth;

import com.google.common.base.Preconditions;
import me.gepron1x.auth.api.AuthProfile;
import me.gepron1x.auth.api.ProfileManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class DefaultProfileManager implements ProfileManager {
    private final Map<UUID, AuthProfile> profiles = new HashMap<>();


    @Nullable
    public AuthProfile getProfile(@NotNull UUID uuid) {
        return profiles.get(uuid);
    }


    @NotNull
    public AuthProfile.Builder profileBuilder() {
        return DefaultAuthProfile.builder();
    }

    @Override
    public void addProfile(@NotNull AuthProfile profile) {
        UUID uniqueId = profile.getUniqueId();
        Preconditions.checkArgument(!profiles.containsKey(uniqueId), "profile with the same uuid already exists");
        profiles.put(uniqueId, profile);
    }

    @Override
    public void removeProfile(@NotNull AuthProfile profile) {
        UUID uniqueId = profile.getUniqueId();
        Preconditions.checkArgument(profiles.remove(uniqueId, profile), "cannot remove unknown profile");
    }

}
