package com.zr.kafka.util;

import java.util.Properties;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月29日 09:28:00
 */
public class ConsumerConnectionInfo {

    //组号
    public static final String GROUP_ID = "test-group";
    public static final String TOPIC_NAME = "test";

    public static Properties getProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "47.100.138.3:9092");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", GROUP_ID);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");

        //从最早的消息开始读取
        props.put("auto.offset.reset", "earliest");
        return props;
    }
}
