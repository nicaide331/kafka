package com.zr.kafka.consumer.demo;

import com.zr.kafka.util.ConsumerConnectionInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月29日 09:26:00
 */
public class ConsumerTest {
    public static void main(String[] args) {
        String topicName = ConsumerConnectionInfo.TOPIC_NAME;

        //配置参数
        Properties properties = ConsumerConnectionInfo.getProperties();

        //创建consumer实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //订阅topic--test
        consumer.subscribe(Arrays.asList(topicName));

        try {
            while (true) {
                //从订阅的topic方法里，获得多个分区的消息
                ConsumerRecords<String, String> records = consumer.poll(5000);
                //业务逻辑处理
                for (ConsumerRecord<String, String> record : records) {
//                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                }
            }
        } finally {
            consumer.close();
        }

    }
}
