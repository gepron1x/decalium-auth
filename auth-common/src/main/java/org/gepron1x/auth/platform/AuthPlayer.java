package org.gepron1x.auth.platform;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.net.InetSocketAddress;
import java.util.UUID;

public interface AuthPlayer extends CmdSender {
    @NotNull UUID getUniqueId();
    @NotNull String getName();
    @NotNull InetSocketAddress getAddress();
    void disconnect(@NotNull Component reason);
    void sendTo(@NotNull ServerInfo info);

}
