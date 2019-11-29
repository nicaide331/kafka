package com.zr.kafka.consumer.reblance;

import com.zr.kafka.util.ConsumerConnectionInfo;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月29日 14:09:00
 */
public class RebalanceTest {
    public static void main(String[] args) {
        Properties properties = ConsumerConnectionInfo.getProperties();
        properties.setProperty("enable.auto.commit", "false");
        final KafkaConsumer consumer = new KafkaConsumer(properties);

        final AtomicLong totalRebalanceTimeMs = new AtomicLong(0L);

        final AtomicLong joinStart = new AtomicLong(0L);

        consumer.subscribe(Arrays.asList("test"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                for (TopicPartition topicPartition : partitions) {
                    System.out.println("将该分区的offset保存到外部存储");
                }
                joinStart.set(System.currentTimeMillis());

            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                //更新总rebalance时长
                totalRebalanceTimeMs.addAndGet(System.currentTimeMillis() - joinStart.get());
                for (TopicPartition topicPartition : partitions) {
                }
            }
        });
    }
}
