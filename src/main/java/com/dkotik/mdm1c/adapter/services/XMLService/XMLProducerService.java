package com.dkotik.mdm1c.adapter.services.XMLService;

import com.dkotik.mdm1c.adapter.ConfigProperties;
import com.dkotik.mdm1c.adapter.dto.MessageWrapper;
import com.dkotik.mdm1c.adapter.dto.XMLMessage.XMLMessageWrapper;
import com.dkotik.mdm1c.adapter.services.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.ProducerTemplate;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XMLProducerService implements Producer {

    private final ProducerTemplate producerTemplate;
    private final ConfigProperties config;

    @Autowired
    public XMLProducerService(ProducerTemplate producerTemplate, ConfigProperties config) {
        this.producerTemplate = producerTemplate;
        this.config = config;
    }

    @Override
    public void sendMessage(MessageWrapper messageWrapper) {
        Map<String, Object> headers = new HashMap<>();
        extractHeaders(messageWrapper, headers);
        String result = marshallerMessageWrapper(messageWrapper);
        producerTemplate.sendBodyAndHeaders(getFullUri(), result, headers);
    }

    private String marshallerMessageWrapper(MessageWrapper messageWrapper) {
        StringWriter writer = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLMessageWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(messageWrapper, writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return writer.toString();
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

