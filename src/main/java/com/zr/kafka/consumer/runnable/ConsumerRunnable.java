package com.zr.kafka.consumer.runnable;

import com.zr.kafka.util.ConsumerConnectionInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import static com.zr.kafka.util.ConsumerConnectionInfo.GROUP_ID;
import static com.zr.kafka.util.ConsumerConnectionInfo.TOPIC_NAME;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月29日 15:51:00
 */
public class ConsumerRunnable implements Runnable {

    //每个线程维护私有的KafkaConsumer实例
    private final KafkaConsumer<String, String> consumer;

    public ConsumerRunnable(String brokerList, String groupId, String topic) {
        Properties properties = ConsumerConnectionInfo.getProperties();
        properties.setProperty("bootstrap.servers", brokerList);
        properties.setProperty("group.id", GROUP_ID);
        this.consumer = new KafkaConsumer<String, String>(properties);
        //分区副本自动分配策略
        consumer.subscribe(Arrays.asList(TOPIC_NAME));
    }

    @Override
    public void run() {
        while (true) {
            //本例使用200毫秒作为获取的超时时间
            ConsumerRecords<String, String> records = consumer.poll(2000);

            for (ConsumerRecord<String, String> record : records) {
                //业务逻辑处理
                System.out.println(Thread.currentThread().getName() + "consumed " + record.partition()
                    + "th message with offset: " + record.offset());
            }
        }
    }
}
