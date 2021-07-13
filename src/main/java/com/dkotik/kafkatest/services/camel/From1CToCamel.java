/*

package com.dkotik.kafkatest.services.camel;

import com.dkotik.kafkatest.ConfigProperties;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class SimpleRouteBuilder extends RouteBuilder {

    ConfigProperties config;

    */
/*@Autowired
    public SimpleRouteBuilder(ConfigProperties config) {
        this.config = config;
    }*//*


    @Override
    public void configure() throws Exception {
        String topicName = "bus_internal";
        //String serializerClass = "serializerClass=kafka.serializer.StringEncoder";
        //String zooKeeper = "zookeeperHost=rusagro-zhir.tengry.com&zookeeperPort=2181";
        //String serializerClass = "serializerClass=org.apache.kafka.common.serialization.StringSerializer";
        //String toRemoteKafka = kafkaServer + "?" + topicName + "&" + zooKeeper + "&" + serializerClass;
        //String topicFromProps = this.config.getBaseTopic();
        String to = "kafka:" + topicName + "?brokers=" + "rusagro-zhir.tengry.com:9093";

        from("direct:from-1c-controller")
                .setHeader(KafkaConstants.KEY, constant("transfer"))
                .setHeader(KafkaConstants.PARTITION_KEY, constant(1))
                .setHeader(KafkaConstants.OVERRIDE_TOPIC, constant("bus_internal"))
                .to(to);

        //String toNew = "kafka:" + "terrasoft" + "?brokers=" + "rusagro-zhir.tengry.com:9092";
        //from(to).to(toNew);
    }
}



*/
