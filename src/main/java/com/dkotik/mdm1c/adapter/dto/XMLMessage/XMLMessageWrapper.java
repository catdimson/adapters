package com.dkotik.mdm1c.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;
import java.util.Set;

@XmlType(name = "message")
@XmlRootElement
public class XMLMessageWrapper implements MessageWrapper {

    @JsonProperty("body")
    String originalMessage;
    @JsonProperty("from")
    String from;
    @JsonProperty("to")
    Set<Recipient> to = new HashSet<>();

    public XMLMessageWrapper() {
    }

    @Override
    public String getOriginalMessage() {
        return originalMessage;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
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


