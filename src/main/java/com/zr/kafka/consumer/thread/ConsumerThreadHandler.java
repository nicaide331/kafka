package com.zr.kafka.consumer.thread;

import com.zr.kafka.util.ConsumerConnectionInfo;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月29日 16:24:00
 */
public class ConsumerThreadHandler<K, V> {

    private final KafkaConsumer<K, V> consumer;
    private ExecutorService executors;
    private final Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();

    public ConsumerThreadHandler(String brokerList, String groupId, String topic) {
        Properties properties = ConsumerConnectionInfo.getProperties();
        properties.setProperty("bootstrap.servers", brokerList);
        properties.setProperty("group.id", groupId);
        properties.setProperty("enable.auto.commit", "false");

        consumer = new KafkaConsumer<K, V>(properties);
        consumer.subscribe(Arrays.asList(topic), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                consumer.commitSync(offsets);
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                offsets.clear();
            }
        });

    }

    public void consume(int threadNumber) {
        executors = new ThreadPoolExecutor(
                threadNumber,
                threadNumber,
                0L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000),
                new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            while (true) {
                ConsumerRecords<K, V> records = consumer.poll(Duration.ofSeconds(1));
                if (!records.isEmpty()) {
                    executors.submit(new ConsumerWorker<>(records, offsets));
                }
                commitOffsets();
            }
        } catch (Exception e) {
            System.out.println("异常处理");
        } finally {
            commitOffsets();
            consumer.close();
        }

    }

    private void commitOffsets() {
        //尽量降低synchronized块对offsets锁定的时间
        Map<TopicPartition, OffsetAndMetadata> unmodfiedMap;
        synchronized (offsets) {
            if (offsets.isEmpty()) {
                return;
            }

            unmodfiedMap = Collections.unmodifiableMap(new HashMap<>(offsets));
            offsets.clear();
        }
        consumer.commitSync(unmodfiedMap);
    }

    public void close() {
        consumer.wakeup();
        executors.shutdown();
    }

}
