package com.dkotik.kafkatest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka-producer")
public class ConfigProperties {

    String topic;
    String broker;
    String brokerPort;
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBrokerPort() {
        return brokerPort;
    }

    public void setBrokerPort(String brokerPort) {
        this.brokerPort = brokerPort;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
