package com.zr.kafka.partition;

import com.zr.kafka.util.ConnectionInfo;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月28日 16:19:00
 */
public class PartitionerTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = ConnectionInfo.getProperties();
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.zr.kafka.partition.AuditPartitioner");
        String topic = "test";
        Producer<String, String> producer = new KafkaProducer<String, String>(properties);
        ProducerRecord nonKeyRecord = new ProducerRecord("test", "non-key record");
        ProducerRecord auditKeyRecord = new ProducerRecord("test", "audit", "audit-key record");
        ProducerRecord nonAuditKeyRecord = new ProducerRecord("test", "other", "non-audit record");
        producer.send(nonKeyRecord).get();
        producer.send(auditKeyRecord).get();
        producer.send(auditKeyRecord).get();
        producer.send(nonAuditKeyRecord).get();
        producer.send(nonAuditKeyRecord).get();
    }
}