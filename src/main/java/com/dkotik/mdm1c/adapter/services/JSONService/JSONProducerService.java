package com.dkotik.mdm1c.adapter.services.JSONService;

import com.dkotik.mdm1c.adapter.ConfigProperties;
import com.dkotik.mdm1c.adapter.dto.MessageWrapper;
import com.dkotik.mdm1c.adapter.services.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JSONProducerService implements Producer {

    private final ProducerTemplate producerTemplate;
    private final ConfigProperties config;

    @Autowired
    public JSONProducerService(ProducerTemplate producerTemplate, ConfigProperties config) {
        this.producerTemplate = producerTemplate;
        this.config = config;
    }

    @Override
    public void sendMessage(MessageWrapper messageWrapper) {

        Map<String, Object> headers = new HashMap<>();
        extractHeaders(messageWrapper, headers);
        String result = serializerMessageWrapper(messageWrapper);
        producerTemplate.sendBodyAndHeaders(getFullUri(), result, headers);
    }

    private String serializerMessageWrapper(MessageWrapper messageWrapper) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(messageWrapper);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void extractHeaders(MessageWrapper messageWrapper, Map<String, Object> headers) {
        headers.put("from", messageWrapper.getFrom());
        List<String> to = new ArrayList<>();
        messageWrapper.getTo().forEach(address -> {
            to.add(address.getAddress().toString());
        });
        headers.put("to", String.join(" ", to));
    }

    private String getFullUri() {
        return "kafka:" + config.getTopic() + "?" +
                "brokers=" + config.getBroker() + ":" + config.getBrokerPort() + "&" +
                "key=" + config.getKey();
    }
}

