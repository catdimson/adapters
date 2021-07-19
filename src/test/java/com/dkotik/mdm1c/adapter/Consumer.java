package com.dkotik.mdm1c.adapter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


import java.util.concurrent.CountDownLatch;

@Component
public class Consumer {

    private volatile CountDownLatch latch = new CountDownLatch(1);
    private volatile String message;

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "#{'${kafka-producer.topic}'}")
    public synchronized void listenTopis(ConsumerRecord<String, String> consumerRecord)  {
        message = consumerRecord.value();
        System.out.println("message:" + message);
        latch.countDown();
        System.out.println("latch:" + latch.getCount());
    }


    public String getMessage() {
        return message;
    }

}
