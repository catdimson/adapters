package com.dkotik.mdm1c.adapter;


import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;



import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class ProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Consumer consumer;

    @Test
    public void sendJsonMessage() throws Exception {
        consumer.clearMessage();
        var json = new String(getClass().getClassLoader().getResourceAsStream("message.json").readAllBytes());
        var responce = mockMvc.perform(
                post("/api/v1/sendMessageJSON").contentType("application/json").content(json))
                .andReturn().getResponse();
        Thread.currentThread().sleep(10000);
        JSONAssert.assertEquals(consumer.getMessage(),json,false);
        assertThat(responce.getStatus()).isEqualTo(200);
    }

    @Test
    public void sendXmlMessage() throws Exception {
        consumer.clearMessage();
        var xml = new String(getClass().getClassLoader().getResourceAsStream("message.xml").readAllBytes());
        var responce = mockMvc.perform(
                post("/api/v1/sendMessageXML").contentType("application/xml").content(xml))
                .andReturn().getResponse();
        Thread.currentThread().sleep(10000);
        assertThat(consumer.getMessage()).isNotNull();
        assertThat(responce.getStatus()).isEqualTo(200);
    }

}
