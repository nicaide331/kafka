package com.zr.kafka.consumer.demo;

import com.zr.kafka.util.ConsumerConnectionInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月29日 09:26:00
 */
public class ConsumerCommitTest {
    public static void main(String[] args) {
        //配置参数
        Properties properties = ConsumerConnectionInfo.getProperties();

        properties.setProperty("enable.auto.commit", "false");

        //创建consumer实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //订阅topic--test
        consumer.subscribe(Arrays.asList(ConsumerConnectionInfo.TOPIC_NAME));

        final int minBatchSize = 10;
        List<ConsumerRecords<String, String>> buffer = new ArrayList<>();

        try {
            while (true) {
                //从订阅的topic方法里，获得多个分区的消息
                ConsumerRecords<String, String> records = consumer.poll(1000);
                //业务逻辑处理
                for (ConsumerRecord<String, String> record : records) {
                    buffer.add(records);
                }
                if (buffer.size() >= minBatchSize) {
//                    inertIntoDb(buffer);
                    System.out.println("插入成功");
                    consumer.commitAsync();
                    buffer.clear();
                }
            }
        } finally {
            consumer.close();
        }

    }
}
