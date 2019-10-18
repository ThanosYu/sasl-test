package com.thanos.sasl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.Test;

import java.util.Collections;
import java.util.Properties;

/**
 * @author Shi Qiang Yu
 * @date 10/18/2019 3:45 PM
 */
public class KafkaConsumer {

    @Test
    public void testConsumer() throws Exception {

        Properties props = new Properties();
        props.put("bootstrap.servers", "10.192.27.232:9093");
        props.put("group.id", "group-" + "CounterKPI");
        props.put("auto.offset.reset", "latest");
        props.put("auto.commit.interval.ms", "1000");
        props.put("enable.auto.commit", "true");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        props.put("security.protocol", "SSL");
        props.put("ssl.truststore.location", "D:\\Develop\\Workspace\\BrownfieldWorkspace\\sasl-test\\src\\main\\java\\com\\thanos\\sasl\\config\\client.truststore.jks");
        props.put("ssl.truststore.password", "123456");
        props.put("ssl.endpoint.identification.algorithm", "");

        org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("test_ssl"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("》》》》》》》》》》》》******************************receive: " + record.value());
            }
        }
    }
}
