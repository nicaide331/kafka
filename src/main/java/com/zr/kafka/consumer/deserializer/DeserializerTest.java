package com.zr.kafka.consumer.deserializer;

import com.zr.kafka.produce.serializer.User;
import com.zr.kafka.util.ConsumerConnectionInfo;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月29日 15:03:00
 */
public class DeserializerTest {
    public static void main(String[] args) {
        Properties properties = ConsumerConnectionInfo.getProperties();
        properties.setProperty("key.deserializer", "com.zr.kafka.consumer.deserializer.UserDeserializer");

        Consumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("test"));
        List<User> users = new ArrayList<>();
        List<ConsumerRecords<String, String>> list = new ArrayList<>();
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));
        try {
            while (true) {
                for (ConsumerRecord consumerRecord : records) {
                    System.out.println("消费成功");
                    Set<TopicPartition> topicPartitions = records.partitions();
                }
            }
        } finally {
            consumer.close();
        }
    }
}
