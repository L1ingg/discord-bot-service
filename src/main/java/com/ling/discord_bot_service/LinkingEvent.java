package com.ling.discord_bot_service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public record LinkingEvent(String id, String username, String code, String provider) {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static LinkingEvent fromJson(String json) throws JsonProcessingException {
        return mapper.readValue(json, LinkingEvent.class);
    }

    public String toJson() throws JsonProcessingException {
        return mapper.writeValueAsString(this);
    }
}
