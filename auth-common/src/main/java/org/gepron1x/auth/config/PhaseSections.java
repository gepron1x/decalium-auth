package org.gepron1x.auth.config;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import space.arim.dazzleconf.annote.ConfDefault;

import java.util.regex.Pattern;

import static space.arim.dazzleconf.annote.ConfDefault.*;

public interface PhaseSections {
    interface PhaseSection {
        Title title();
        Component success();
    }

    interface RegistrationSection extends PhaseSection {
        @Override
        @DefaultMap({
                        "title", "<blue>Зарегистрируйтесь",
                        "subtitle", "Введите в чат желаемый пароль",
                        "times", "0, 99999, 0"
                })
        Title title();

        @Override
        @DefaultString("Вы успешно прошли регистрацию.")
        Component success();
        int minPasswordLength();
        @DefaultString(".+")
        Pattern passwordPattern();

    }
    interface LoginSection extends PhaseSection {
        @Override
        @DefaultMap({
                "title", "<blue>Войдите",
                "subtitle", "Введите в чат пароль",
                "times", "0, 99999, 0"
        })
        Title title();

        @Override
        @DefaultString("Пароль верный.")
        Component success();
    }
    interface VerificationSection extends PhaseSection {
        @Override
        @DefaultMap({
                "title", "<blue>Пройдите верификацию",
                "subtitle", "Отправьте в чат код, который вам отправил бот.",
                "times", "0, 99999, 0"
        })
        Title title();

        @Override
        @DefaultString("Код верный.")
        Component success();
    }


}
