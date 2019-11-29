package com.zr.kafka.produce.partition;


import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.Configurable;

import java.io.Closeable;
import java.io.IOException;

/**
 * 自定义分区
 *
 * @author nicaide
 * @date 2019年11月28日 15:56:00
 */
public interface Partitioner extends Configurable, Closeable {

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster);


    @Override
    default void close() throws IOException {

    }
}
