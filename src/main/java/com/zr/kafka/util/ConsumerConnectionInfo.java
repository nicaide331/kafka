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

        //检测失败的时间---默认10s
//        props.put("session.timeout.ms", "10000");

        //定期关闭空闲的Socket连接
//        props.put("connections.max.idle.ms", "-1");

        //检测失败后，开启reBalance,值要小于session.timeout.ms
//        props.put("session.timeout.ms", "5000");

        //处理逻辑最大时间
        props.put("max.poll.interval.ms", "60000");

        //单次获取数据的最大字节数
//        props.put("fetch.max.bytes", "102400");

        //单次poll调用返回的最大消息数--默认500
        props.put("max.poll.records", "500");

        //是否自动提交位移
        props.put("enable.auto.commit", "true");

        props.put("auto.commit.interval.ms", "1000");

        //从最早的消息开始读取
        props.put("auto.offset.reset", "earliest");
        return props;
    }
}
