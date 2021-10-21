package org.gepron1x.auth.storage;

import me.gepron1x.auth.api.AuthProfile;

import java.net.InetAddress;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface ProfileStorage extends Storage {
    AuthProfile loadProfile(UUID uuid);
    AuthProfile loadProfile(String username);
    List<AuthProfile> loadProfiles();
    void saveProfile(AuthProfile profile);
    void setPassword(AuthProfile profile, String hashedPassword);
    void addLoggedInAddress(AuthProfile profile, InetAddress address, Instant instant);
    void removeLoggedInAddress(AuthProfile profile, InetAddress address);

}
