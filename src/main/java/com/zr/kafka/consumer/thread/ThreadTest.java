package com.zr.kafka.consumer.thread;

import static com.zr.kafka.util.ConsumerConnectionInfo.GROUP_ID;
import static com.zr.kafka.util.ConsumerConnectionInfo.TOPIC_NAME;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月29日 16:57:00
 */
public class ThreadTest {
    public static void main(String[] args) {
        String brokerList = "47.100.138.3:9092";
        String groupId = GROUP_ID;
        String topic = TOPIC_NAME;

        final ConsumerThreadHandler<byte[], byte[]> handler = new ConsumerThreadHandler<>(brokerList, groupId, topic);
        final int cpuCount = Runtime.getRuntime().availableProcessors();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.consume(cpuCount);
            }
        };

        new Thread(runnable).start();

        //20秒后自动停止测试程序
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            System.out.println("异常");
        }

        System.out.println("Starting to close the consumer...");
        handler.close();

    }
}
