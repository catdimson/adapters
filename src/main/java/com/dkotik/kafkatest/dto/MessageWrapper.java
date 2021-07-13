package com.dkotik.kafkatest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

public class MessageWrapper {

    @JsonProperty("body")
    String originalMessage;
    @JsonProperty("from")
    String from;
    @JsonProperty("to")
    Set<Recipient> to = new HashSet<>();

    public MessageWrapper() {
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public String getFrom() {
        return from;
    }

    public Set<Recipient> getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "MessageWrapper{" +
                "originalMessage='" + originalMessage + '\'' +
                ", from='" + from + '\'' +
                ", to=" + to +
                '}';
    }
}

