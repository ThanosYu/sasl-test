package com.thanos.sasl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Collections;
import java.util.Properties;

/**
 * @author Shi Qiang Yu
 * @date 7/30/2019 5:10 PM
 */
public class KafkaSSL {

    @Test
    public void testProducer() throws Exception {

        Properties props = new Properties();
        props.put("bootstrap.servers", "10.192.30.106:9093");
        props.put("acks", "0");
        props.put("retries", 1);
        props.put("batch.size", 16384);
        props.put("linger.ms", 0);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        props.put("security.protocol", "SSL");
        props.put("ssl.truststore.location", "D:\\Develop\\Workspace\\BrownfieldWorkspace\\sasl-test\\src\\main\\java\\com\\thanos\\sasl\\config\\client.truststore.jks");
        props.put("ssl.truststore.password", "123456");
        props.put("ssl.endpoint.identification.algorithm", "");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        while (true) {
            producer.send(new ProducerRecord<>("test_ssl", "key", "wenke"));
            System.out.println("******************************send: " + "wenke");
            Thread.sleep(5000);
        }
    }

    @Test
    public void testConsumer() throws Exception {

        Properties props = new Properties();
        props.put("bootstrap.servers", "10.192.30.106:9093");
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

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("test_ssl"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("******************************receive: " + record.value());
            }
        }
    }
}