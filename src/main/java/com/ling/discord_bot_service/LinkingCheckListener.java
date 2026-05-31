package com.ling.discord_bot_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class LinkingCheckListener extends ListenerAdapter {
    private static final String LINK_COMMAND_NAME = "link";
    private static final String CODE_OPTION_NAME = "code";
    private static final String PROVIDER = "discord";
    private static final String TOPIC_NAME = "account.linking";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public LinkingCheckListener(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals(LINK_COMMAND_NAME)) {
            try {
                User user = event.getUser();
                kafkaTemplate.send(TOPIC_NAME, new LinkingEvent(user.getId(), user.getName(), event.getOption(CODE_OPTION_NAME).getAsString(), PROVIDER).toJson());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        event.reply("In process...").setEphemeral(true).queue();
    }
}
