package com.ling.discord_bot_service;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DiscordBotServiceApplication {

    @Value("${bot.token}")
    private String token;

    private final List<ListenerAdapter> listeners;

    public DiscordBotServiceApplication(List<ListenerAdapter> listeners) {
        this.listeners = listeners;
    }

    public static void main(String[] args) {
		SpringApplication.run(DiscordBotServiceApplication.class, args);
	}

    @Bean
    public JDA jda() {
        return JDABuilder.createDefault(token, GatewayIntent.GUILD_MEMBERS, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(listeners.toArray())
                .build();
    }

}
