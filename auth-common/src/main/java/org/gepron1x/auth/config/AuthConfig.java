package org.gepron1x.auth.config;

import space.arim.dazzleconf.annote.ConfKey;
import space.arim.dazzleconf.annote.SubSection;

import static space.arim.dazzleconf.annote.ConfDefault.DefaultInteger;


public interface AuthConfig {
    @SubSection PhaseSections phases();

    @DefaultInteger(16)
    @ConfKey("max-password-length")
    int maxPasswordLength();

    @DefaultInteger(5)
    @ConfKey("min-password-length")
    int minPasswordLength();


}
