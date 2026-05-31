package com.ling.discord_bot_service;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.stereotype.Component;

@Component
public class LinkingCommand extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        event.getJDA().updateCommands().addCommands(
                Commands.slash("link", "Link your account.")
                    .addOption(OptionType.STRING, "code", "Code that you get")).queue();
    }
}
