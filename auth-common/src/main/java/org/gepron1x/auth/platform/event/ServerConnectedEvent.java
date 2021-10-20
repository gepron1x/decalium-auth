package org.gepron1x.auth.platform.event;

import net.kyori.event.Cancellable;
import org.gepron1x.auth.platform.AuthPlayer;
import org.gepron1x.auth.platform.ServerInfo;
import org.jetbrains.annotations.NotNull;

public interface ServerConnectedEvent extends Cancellable {
    @NotNull AuthPlayer getPlayer();
    @NotNull ServerInfo getServer();

}
