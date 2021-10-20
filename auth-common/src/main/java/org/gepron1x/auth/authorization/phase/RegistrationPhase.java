package org.gepron1x.auth.authorization.phase;

import org.gepron1x.auth.config.PhaseSections;
import org.gepron1x.auth.platform.AuthPlayer;

import java.util.regex.Pattern;

public class RegistrationPhase implements AuthPhase {

    private final PhaseSections.RegistrationSection section;

    public RegistrationPhase(PhaseSections.RegistrationSection section) {

        this.section = section;
    }
    @Override
    public Result process(AuthPlayer player, String input) {
        if(input.length() < section.minPasswordLength()) {
            return Result.FAILURE;
        }
        if(!section.passwordPattern().matcher(input).matches()) {
            return Result.FAILURE;
        }


    }

    @Override
    public void apply(AuthPlayer player) {

    }

    @Override
    public void finish(AuthPlayer player) {

    }
}
