package com.dkotik.mdm1c.adapter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

@Component
public class Consumer {

    private SynchronousQueue<String> message = new SynchronousQueue<>() ;

    @KafkaListener(topics = "#{'${kafka-producer.topic}'}")
    public void listenTopis(ConsumerRecord<String, String> consumerRecord) throws InterruptedException {
        message.put(consumerRecord.value());
    }

    public String getMessage() throws InterruptedException {
        return message.take();
    }

}
