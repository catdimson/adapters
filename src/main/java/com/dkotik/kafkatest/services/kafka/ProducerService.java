package com.dkotik.kafkatest.services.kafka;

import com.dkotik.kafkatest.ConfigProperties;
import com.dkotik.kafkatest.dto.MessageWrapper;
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
public class ProducerService {

    private final ProducerTemplate producerTemplate;
    private final ConfigProperties config;

    @Autowired
    public ProducerService(ProducerTemplate producerTemplate, ConfigProperties config) {
        this.producerTemplate = producerTemplate;
        this.config = config;
    }

    public void sendMessage(MessageWrapper messageWrapper) throws JsonProcessingException {

        Map<String, Object> headers = new HashMap<>();

        headers.put("from", messageWrapper.getFrom());
        List<String> to = new ArrayList<>();
        messageWrapper.getTo().forEach(address -> {
            to.add(address.getAddress().toString());
        });
        headers.put("to", String.join(",", to));

        ObjectMapper objectMapper = new ObjectMapper();
        this.producerTemplate.sendBodyAndHeaders(
                getFullUri(),
                objectMapper.writeValueAsString(messageWrapper),
                headers
        );
        // "kafka:bus_internal?brokers=rusagro-zhir.tengry.com:9093",
        //this.producerTemplate.sendBodyAndHeaders("direct:from-1c-controller", objectMapper.writeValueAsString(messageWrapper), headers);
        //this.producerTemplate.sendBody("direct:from-1c-controller", ExchangePattern.InOut, message);

    }

    private String getFullUri() {
        return "kafka:" + config.getTopic() + "?" +
                "brokers=" + config.getBroker() + ":" + config.getBrokerPort() + "&" +
                "key=" + config.getKey();
    }
}

