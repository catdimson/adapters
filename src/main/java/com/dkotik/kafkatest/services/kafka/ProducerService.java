package com.dkotik.kafkatest.services.kafka;

import com.dkotik.kafkatest.dto.MessageWrapper;
import com.dkotik.kafkatest.dto.SystemsAddress;
import com.dkotik.kafkatest.models.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProducerService {
    //private static final String TOPIC = "1c_mdm";

    //private final KafkaTemplate<String, Message> kafkaTemplate;
    private final ProducerTemplate producerTemplate;

    @Autowired
    public ProducerService(/*KafkaTemplate<String, Message> kafkaTemplate,*/ ProducerTemplate producerTemplate) {
        //this.kafkaTemplate = kafkaTemplate;
        this.producerTemplate = producerTemplate;
    }

    public void sendMessage(MessageWrapper message) throws JsonProcessingException {

        Map<String, Object> headers = new HashMap<>();
//        String sender = new Random().nextBoolean() ? "1c_mdm" : "1c_upp";
//        String recipient = new Random().nextBoolean() ? "crm_1" : "crm_2";

        // назначаем отправителя
        headers.put("from", message.getFrom());
        // назначаем получаетелей
        // List<SystemsAddress> to = new ArrayList<>();
        message.getTo().forEach(address -> {
            headers.put(address.getSystemsAddress().toString(), address.getSystemsAddress().getTopic());
        });

        System.out.println(headers);

        System.out.println("--- СООБЩЕНИЕ БЫЛО ОТПРАВЛЕНО ---");
        //Func<Integer> getSquare = (x) -> x * x;
        //Stream<Integer> stream = Stream.of(1,2,3,4,5);
        //System.out.println(getSquare((x) -> x * x));
        //this.kafkaTemplate.send(TOPIC, message);
        //!!!!!!!!!!!!!
        ObjectMapper objectMapper = new ObjectMapper();
        this.producerTemplate.sendBodyAndHeaders("direct:from-1c-controller", objectMapper.writeValueAsString(message), headers);
        //this.producerTemplate.sendBody("direct:from-1c-controller", ExchangePattern.InOut, message);

    }

    // Для Вали
    public void sendMessage(String body) {
        Map<String, Object> headers = new HashMap<>();
        String sender = new Random().nextBoolean() ? "MDM_1C" : "1c_upp";
        String recipient = new Random().nextBoolean() ? "crm_1" : "crm_2";
        headers.put("from", sender);
        headers.put("to", recipient);

        System.out.println("--- СООБЩЕНИЕ БЫЛО ОТПРАВЛЕНО ---");
        //Func<Integer> getSquare = (x) -> x * x;
        //Stream<Integer> stream = Stream.of(1,2,3,4,5);
        //System.out.println(getSquare((x) -> x * x));
        //this.kafkaTemplate.send(TOPIC, message);
        this.producerTemplate.sendBodyAndHeaders("direct:from-1c-controller", body, headers);
        //this.producerTemplate.sendBody("direct:from-1c-controller", ExchangePattern.InOut, message);
    }

    /*public Integer getSquare(Func<Integer> function) {
        return function.square(5);
    }*/

    /*@Component
    public class SimpleRouteBuilder extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            //String kafkaServer = "kafka:rusagro-zhir.tengry.com:9092";
            String topicName = "1c_mdm";
            //String serializerClass = "serializerClass=kafka.serializer.StringEncoder";
            //String zooKeeper = "zookeeperHost=rusagro-zhir.tengry.com&zookeeperPort=2181";
            //String serializerClass = "serializerClass=org.apache.kafka.common.serialization.StringSerializer";
            //String toRemoteKafka = kafkaServer + "?" + topicName + "&" + zooKeeper + "&" + serializerClass;
            String to = "kafka:" + topicName + "?brokers=" + "rusagro-zhir.tengry.com:9092";
            from("direct:from-1c-controller").to(to);

            String toNew = "kafka:" + "terrasoft" + "?brokers=" + "rusagro-zhir.tengry.com:9092";
            //from(to).to(toNew);
        }
    }*/
}

