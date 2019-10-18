package com.thanos.sasl;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Properties;

/**
 * @author Shi Qiang Yu
 * @date 7/30/2019 5:10 PM
 */
public class KafkaProducer {

    @Test
    public void testProducer() throws Exception {

        Properties props = new Properties();
        props.put("bootstrap.servers", "10.192.27.232:9093");
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

        org.apache.kafka.clients.producer.KafkaProducer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
        while (true) {
            producer.send(new ProducerRecord<>("test_ssl", "key", "wenke"));
            System.out.println("《《《《《《《《《《《《《《《《《《******************************send: " + "wenke");
            Thread.sleep(5000);
        }
    }
}