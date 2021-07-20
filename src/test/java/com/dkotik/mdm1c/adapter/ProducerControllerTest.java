package com.dkotik.mdm1c.adapter;


import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class ProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void sendJsonMessage() throws Exception {
        Consumer.init();
        var json = new String(getClass().getClassLoader().getResourceAsStream("message.json").readAllBytes(),StandardCharsets.UTF_8);

        var responce = mockMvc.perform(
                post("/api/v1/sendMessageJSON").contentType("application/json").content(json))
                .andReturn().getResponse();
        Consumer.getLatch().await();

        assertThat(Consumer.getLatch().getCount()).isEqualTo(0);
        JSONAssert.assertEquals(json, Consumer.getMessage(), false);
        assertThat(responce.getStatus()).isEqualTo(200);
    }

    @Test
    public void sendXmlMessage() throws Exception {
        Consumer.init();
        var xml = new String(getClass().getClassLoader().getResourceAsStream("message.xml").readAllBytes());
        var xsd = getClass().getClassLoader().getResourceAsStream("message.xsd");


        var responce = mockMvc.perform(
                post("/api/v1/sendMessageXML").contentType("application/xml").content(xml))
                .andReturn().getResponse();

        Consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        var message = new ByteArrayInputStream(Consumer.getMessage().getBytes(StandardCharsets.UTF_8));

        assertThat(Consumer.getLatch().getCount()).isEqualTo(0);
        assertThat(validateXML(message, xsd)).as("Некоректный xml").isTrue();
        assertThat(responce.getStatus()).isEqualTo(200);
    }

    private boolean validateXML(InputStream xml, InputStream xsd) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
