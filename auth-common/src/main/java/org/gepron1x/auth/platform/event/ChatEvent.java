package org.gepron1x.auth.platform.event;

import net.kyori.event.Cancellable;
import org.gepron1x.auth.platform.AuthPlayer;
import org.jetbrains.annotations.NotNull;

public interface ChatEvent extends Cancellable {

    @NotNull AuthPlayer getPlayer();
    @NotNull String getMessage();
    void setMessage(@NotNull String message);
}
