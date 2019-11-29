package com.zr.kafka.produce.serializer;

import com.zr.kafka.util.ProduceConnectionInfo;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月28日 17:22:00
 */
public class SerializerTest {
    public static void main(String[] args){
        Properties properties = ProduceConnectionInfo.getProperties();
        properties.setProperty("value.serializer", "com.zr.kafka.produce.serializer.UserSerializer");

        String topic = "test";

        Producer<String, User> producer = new KafkaProducer<>(properties);

        User user = new User("First", "Last", 22, "China");
        ProducerRecord<String, User> record = new ProducerRecord<>("test", user);
        try {
            for (int i = 0; i< 100; i++)
                producer.send(record).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        producer.close();
    }
}
