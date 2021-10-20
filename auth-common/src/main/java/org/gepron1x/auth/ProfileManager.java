package org.gepron1x.auth;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ProfileManager {
    private final Map<UUID, AuthProfile> profiles = new HashMap<>();


    @Nullable
    public AuthProfile getProfile(UUID uuid) {
        return profiles.get(uuid);
    }

    public AuthProfile.Builder profileBuilder() {
        return AuthProfile.builder();
    }
    public void addProfile(AuthProfile profile) {

    }

}
