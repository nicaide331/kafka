package com.zr.kafka.consumer.runnable;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月29日 16:05:00
 */
public class ConsumerGroup {

    private List<ConsumerRunnable> consumers;

    public ConsumerGroup(int consumerNum, String groupId, String topic, String brokerList) {
        consumers = new ArrayList<>(consumerNum);

        for (int i = 0; i < consumerNum; i++) {
            ConsumerRunnable consumerRunnable = new ConsumerRunnable(brokerList, groupId, topic);
            consumers.add(consumerRunnable);
        }
    }
    public void execute() {
        for (ConsumerRunnable task : consumers) {
            new Thread(task).start();
        }
    }
}
