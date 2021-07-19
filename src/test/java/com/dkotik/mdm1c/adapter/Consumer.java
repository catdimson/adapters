package com.dkotik.mdm1c.adapter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


import java.util.concurrent.CountDownLatch;

@Component
public class Consumer {

    private CountDownLatch latch = new CountDownLatch(2);
    private String message;

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "#{'${kafka-producer.topic}'}")
    public void listenTopis(ConsumerRecord<String, String> consumerRecord)  {
        message = consumerRecord.value();
        latch.countDown();
    }

    public void init() {
        latch = new CountDownLatch(1);
    }


    public String getMessage() {
        return message;
    }

}
