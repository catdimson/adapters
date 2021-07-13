package com.dkotik.mdm1c.adapter.dto.XMLMessage;

import com.dkotik.mdm1c.adapter.dto.Recipient;
import com.dkotik.mdm1c.adapter.dto.MessageWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlType(propOrder = {"originalMessage", "from", "to"}, name = "messageWrapper")
@XmlRootElement(name = "message")
public class XMLMessageWrapper implements MessageWrapper {

    @XmlElement(name = "body")
    String originalMessage;
    @XmlElement(name = "from")
    String from;
    @XmlElement(name = "to")
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


