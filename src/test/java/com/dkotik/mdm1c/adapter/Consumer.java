package com.dkotik.mdm1c.adapter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class Consumer {
    private Queue<String> message = new LinkedList<>();

    @KafkaListener(topics = "#{'${kafka-producer.topic}'}")
    public void listenTopis(ConsumerRecord<String, String> consumerRecord) {
        message.add(consumerRecord.value());
    }

    public String getMessage() {
        return message.poll();
    }

}
