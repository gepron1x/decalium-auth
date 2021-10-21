package org.gepron1x.auth.authorization.phase;

import com.google.common.hash.HashFunction;
import me.gepron1x.auth.api.ProfileManager;
import net.kyori.adventure.text.minimessage.Template;
import org.gepron1x.auth.config.AuthConfig;
import org.gepron1x.auth.config.MessagesConfig;
import org.gepron1x.auth.config.PhaseSections;
import org.gepron1x.auth.platform.AuthPlayer;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

public class RegistrationPhase implements AuthPhase {

    private final ProfileManager pm;
    private String password;
    private final HashFunction hashFunction;
    private final int maxLength, minLength;
    private final MessagesConfig messages;
    private final PhaseSections.RegistrationSection section;

    public RegistrationPhase(ProfileManager pm, HashFunction hashFunction, AuthConfig config, MessagesConfig messages) {
        this.pm = pm;
        this.hashFunction = hashFunction;
        this.messages = messages;
        this.section = config.phases().registration();
        this.maxLength = config.maxPasswordLength();
        this.minLength = config.minPasswordLength();
    }

    @Override
    public Result process(AuthPlayer player, String input) {
        Template password = Template.template("password", input);
        if (input.length() < minLength) {
            player.sendMessage(messages.passwordTooShort().with(password).with("min-length", minLength));
            return Result.FAILURE;
        }
        if (input.length() > maxLength) {
            player.sendMessage(messages.passwordTooLong().with(password).with("max-length", maxLength));
            return Result.FAILURE;
        }
        if (!section.passwordPattern().matcher(input).matches()) {
            return Result.FAILURE;
        }
        this.password = input;
        section.success();
        return Result.SUCCESS;


    }

    @Override
    public void apply(AuthPlayer player) {
        player.showTitle(section.title());
    }

    @Override
    public void finish(AuthPlayer player) {
        pm.buildAndAdd(builder -> {
            builder.uuid(player.getUniqueId())
                    .password(hashFunction.hashString(password, StandardCharsets.UTF_8).toString())
                    .addLoggedInAddress(player.getAddress().getAddress(), Instant.now());
        });
    }
}
