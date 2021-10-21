package org.gepron1x.auth.config;

import org.gepron1x.auth.util.Message;
import space.arim.dazzleconf.annote.ConfKey;

import static space.arim.dazzleconf.annote.ConfDefault.DefaultString;

public interface MessagesConfig {
    @DefaultString("<red>Ошибка! Пароль слишком короткий.")
    @ConfKey("password-too-short")
    Message passwordTooShort();

    @DefaultString("<red>Ошибка! Пароль слишком длинный.")
    @ConfKey("password-too-long")
    Message passwordTooLong();
}
