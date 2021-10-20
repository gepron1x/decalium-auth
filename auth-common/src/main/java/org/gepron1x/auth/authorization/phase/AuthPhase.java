package org.gepron1x.auth.authorization.phase;

import org.gepron1x.auth.platform.AuthPlayer;

public interface AuthPhase {

    Result process(AuthPlayer player, String input);

    void apply(AuthPlayer player);
    void finish(AuthPlayer player);


    enum Result {
        SUCCESS,
        FAILURE,
        INTERRUPT
    }
}
