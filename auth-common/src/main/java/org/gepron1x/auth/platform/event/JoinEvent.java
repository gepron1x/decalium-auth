package org.gepron1x.auth.platform.event;

import org.gepron1x.auth.platform.AuthPlayer;
import org.jetbrains.annotations.NotNull;

public interface JoinEvent {
    @NotNull AuthPlayer getPlayer();
}
