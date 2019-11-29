package com.zr.kafka.consumer.demo;

import com.zr.kafka.util.ConsumerConnectionInfo;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月29日 11:06:00
 */
public class AsyncCommitTest {
    public static void main(String[] args) {
        //配置参数
        Properties properties = ConsumerConnectionInfo.getProperties();

        //创建consumer实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //订阅topic--test
        consumer.subscribe(Arrays.asList(ConsumerConnectionInfo.TOPIC_NAME));

        List<ConsumerRecords<String, String>> buffer = new ArrayList<>();

        try {
            while (true) {
                //从订阅的topic方法里，获得多个分区的消息
                ConsumerRecords<String, String> records = consumer.poll(1000);

                //获取分区
                for (TopicPartition partition : records.partitions()) {
                    List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                    for (ConsumerRecord<String, String> record: partitionRecords) {
                        System.out.println(record.offset() + ": " +record.value());
                    }
                    //位移
                    long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                    Map map = Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset +1 ));
                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
                }
            }
        } finally {
            consumer.close();
        }

    }
}
