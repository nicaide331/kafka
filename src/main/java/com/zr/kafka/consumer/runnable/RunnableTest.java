package com.zr.kafka.consumer.runnable;

import static com.zr.kafka.util.ConsumerConnectionInfo.GROUP_ID;
import static com.zr.kafka.util.ConsumerConnectionInfo.TOPIC_NAME;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月29日 16:09:00
 */
public class RunnableTest {
    public static void main(String[] args) {
        String brokerList = "47.100.138.3:9092";
        String groupId = GROUP_ID;
        String topic = TOPIC_NAME;
        int consumerNum = 3;

        ConsumerGroup consumerGroup = new ConsumerGroup(consumerNum, groupId, topic, brokerList);
        consumerGroup.execute();
    }
}
