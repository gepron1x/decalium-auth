package org.gepron1x.auth.authorization.phase;

import me.gepron1x.auth.api.ProfileManager;
import org.gepron1x.auth.config.AuthConfig;
import org.gepron1x.auth.config.MessagesConfig;
import org.gepron1x.auth.platform.AuthPlayer;

public class LoginPhase implements AuthPhase {
    private final ProfileManager pm;
    private final AuthConfig config;
    private final MessagesConfig messages;
    private int attempts;

    public LoginPhase(ProfileManager pm, AuthConfig config, MessagesConfig messages) {
        this.attempts = config.phases().login().maxAttempts();

        this.pm = pm;
        this.config = config;
        this.messages = messages;
    }

    @Override
    public Result process(AuthPlayer player, String input) {
        return null;
    }

    @Override
    public void apply(AuthPlayer player) {

    }

    @Override
    public void finish(AuthPlayer player) {

    }
}
