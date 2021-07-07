package com.dkotik.kafkatest.kafka;

import com.dkotik.kafkatest.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ProducerService {
    private static final String TOPIC = "1c_mdm";

    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    public ProducerService(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Message message) {
        System.out.println("--- СООБЩЕНИЕ БЫЛО ОТПРАВЛЕНО ---");
        Func<Integer> getSquare = (x) -> x * x;
        //Stream<Integer> stream = Stream.of(1,2,3,4,5);

        System.out.println(getSquare((x) -> x * x));
        this.kafkaTemplate.send(TOPIC, message);
    }

    public Integer getSquare(Func<Integer> function) {
        return function.square(5);
    }
}

