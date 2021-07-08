
package com.dkotik.kafkatest.services.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;

@Component
class SimpleRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //String kafkaServer = "kafka:rusagro-zhir.tengry.com:9092";
        String topicName = "bus_internal";
        //String serializerClass = "serializerClass=kafka.serializer.StringEncoder";
        //String zooKeeper = "zookeeperHost=rusagro-zhir.tengry.com&zookeeperPort=2181";
        //String serializerClass = "serializerClass=org.apache.kafka.common.serialization.StringSerializer";
        //String toRemoteKafka = kafkaServer + "?" + topicName + "&" + zooKeeper + "&" + serializerClass;
        String to = "kafka:" + topicName + "?brokers=" + "rusagro-zhir.tengry.com:9092&key=qwerty";
        from("direct:from-1c-controller")
                //.setHeader(KafkaConstants.KEY, constant("const-value"))
                .setHeader(KafkaConstants.PARTITION_KEY, constant(2))
                //.setHeader(KafkaConstants.OVERRIDE_TIMESTAMP, constant(11111111111111111L))
                .setHeader(KafkaConstants.OVERRIDE_TOPIC, constant("1c_upp"))
                .setBody(constant("rewriting message 3"))
                .to(to);

        //String toNew = "kafka:" + "terrasoft" + "?brokers=" + "rusagro-zhir.tengry.com:9092";
        //from(to).to(toNew);
    }
}



