package com.zr.kafka.util;

import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月28日 16:27:00
 */
public class ConnectionInfo {

    public static Properties getProperties() {
        Properties props = new Properties();
        //创建Kafka服务器的连接，比如k1:9092
        props.put("bootstrap.servers", "47.100.138.3:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("acks", "-1");
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        props.put("retries", 100);  //重试3次
//        props.put("batch.size", 323840);  //批处理数量
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 1048576);   //16kb
        props.put("linger.ms", 10);
//        props.put("buffer.memory", 3354432); //32MB
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 3354432);
        props.put("max.block.ms", 3000);

        //最大消息尺寸
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 10485760);

        //压缩
//        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");
        return props;
    }
}
