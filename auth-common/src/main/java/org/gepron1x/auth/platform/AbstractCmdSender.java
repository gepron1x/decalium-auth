package org.gepron1x.auth.platform;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCmdSender implements CmdSender {
    private final Audience audience;

    public AbstractCmdSender(Audience audience) {

        this.audience = audience;
    }
    @Override
    public void sendMessage(@NotNull Identity source, @NotNull Component message, @NotNull MessageType type) {
        audience.sendMessage(source, message, type);
    }

    @Override
    public void sendActionBar(@NotNull Component message) {
        audience.sendActionBar(message);
    }

    @Override
    public void showTitle(@NotNull Title title) {
        audience.showTitle(title);
    }

    @Override
    public <T> void sendTitlePart(@NotNull TitlePart<T> part, @NotNull T value) {
        audience.sendTitlePart(part, value);
    }

    @Override
    public void clearTitle() {
        audience.clearTitle();
    }

    @Override
    public void resetTitle() {
        audience.resetTitle();
    }
}
