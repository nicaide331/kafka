package com.zr.kafka.produce.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月28日 16:04:00
 */
public class AuditPartitioner implements Partitioner {

    private Random random;

    /**
     * 设定某个分区
     * @param topic
     * @param keyObj
     * @param keyBytes
     * @param value
     * @param valueBytes
     * @param cluster
     * @return
     */
    @Override
    public int partition(String topic, Object keyObj, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
//        return 1;
        String key = (String) keyObj;
        List<PartitionInfo> partitionInfoList = cluster.availablePartitionsForTopic(topic);
        int partitionCount = partitionInfoList.size();
        int auditPartition = partitionCount - 1;
        return key == null || key.isEmpty() || !key.contains("audit") ? random.nextInt(partitionCount -1) : auditPartition;
    }

    @Override
    public void close() {

    }

    /**
     * 配置资源
     * @param map
     */
    @Override
    public void configure(Map<String, ?> map) {
        random = new Random();
    }
}
