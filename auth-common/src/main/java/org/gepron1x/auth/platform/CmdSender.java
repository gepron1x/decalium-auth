package org.gepron1x.auth.platform;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.jetbrains.annotations.NotNull;

public interface CmdSender extends Audience {
    @Override
    void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type);


    @Override
    void sendActionBar(@NotNull Component message);

    @Override
    void showTitle(@NotNull Title title);

    @Override
    <T> void sendTitlePart(@NotNull TitlePart<T> part, @NotNull T value);


    @Override
    void clearTitle();

    @Override
    void resetTitle();

}
