package com.dkotik.mdm1c.adapter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private String message;

    @KafkaListener(topics = {"embedded-test-topic"})
    public void listenTopis(ConsumerRecord<String,String> consumerRecord) {
        message = consumerRecord.value();
    }

    public String getMessage() {
        return message;
    }

    public void clearMessage(){
        message=null;
    }
}
