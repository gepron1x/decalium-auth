package org.gepron1x.auth.authorization;

import org.gepron1x.auth.authorization.phase.AuthPhase;
import org.gepron1x.auth.platform.AuthPlayer;

import java.util.Queue;

public class AuthSession {
    private final Queue<AuthPhase> phases;
    private final AuthPlayer player;

    public AuthSession(Queue<AuthPhase> phases, AuthPlayer player) {

        this.phases = phases;
        this.player = player;
    }

    public void start() {
        AuthPhase phase = phases.peek();
        if(phase != null) phase.apply(player);
    }
    
    public boolean process(String input) {
        AuthPhase phase = phases.peek();
        if (phase == null) return true;
        AuthPhase.Result result = phase.process(player, input);
        if (result != AuthPhase.Result.SUCCESS) return false;


        phases.remove();
        phase.finish(player);

        AuthPhase newPhase = phases.peek();
        if (newPhase == null) return true;
        newPhase.apply(player);

        return false;
    }

}
