package org.gepron1x.auth.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.Template;
import net.kyori.adventure.text.minimessage.template.TemplateResolver;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class Message implements ComponentLike {
    private final MiniMessage miniMessage;
    private final String value;
    private final List<Template> templates;

    public Message(MiniMessage miniMessage, String value, List<Template> templates) {

        this.miniMessage = miniMessage;
        this.value = value;
        this.templates = templates;
    }

    public Message with(@NotNull Template template) {
        ArrayList<Template> temp = new ArrayList<>(this.templates);
        temp.add(template);
        return new Message(this.miniMessage, this.value, temp);
    }

    public Message with(@NotNull Collection<Template> templates) {
        if (templates.isEmpty()) return this;
        ArrayList<Template> temp = new ArrayList<>(this.templates);
        temp.addAll(templates);
        return new Message(this.miniMessage, this.value, temp);
    }

    public Message with(@NotNull Template... templates) {
        if (templates.length == 0) return this;
        return with(Arrays.asList(templates));
    }

    public Message with(String key, String value) {
        return with(Template.template(key, value));
    }

    public Message with(String key, int value) {
        return with(key, String.valueOf(value));
    }

    public Message with(String key, long value) {
        return with(key, String.valueOf(value));
    }

    public Message with(String key, byte value) {
        return with(key, String.valueOf(value));
    }

    public Message with(String key, short value) {
        return with(key, String.valueOf(value));
    }

    public Message with(String key, float value) {
        return with(key, String.valueOf(value));
    }

    public Message with(String key, double value) {
        return with(key, String.valueOf(value));
    }

    public Message with(String key, char value) {
        return with(key, String.valueOf(value));
    }

    public Message with(String key, boolean value) {
        return with(key, String.valueOf(value));
    }


    public Message with(String key, Component value) {
        return with(Template.template(key, value));
    }

    public Message with(String key, ComponentLike value) {
        return with(Template.template(key, value));
    }


    @Override
    public @NotNull Component asComponent() {
        return miniMessage.deserialize(value, TemplateResolver.templates(templates));
    }
}
