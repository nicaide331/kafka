package com.zr.kafka.produce.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月28日 18:00:00
 */
public class TimeStampPrependerInterceptor implements ProducerInterceptor<String, String> {

    @Override
    public void configure(Map<String, ?> map) {

    }

    /**
     * 该方法封装进KafkaProducer.send方法中，
     * @param producerRecord
     * @return
     */
    @Override
    public ProducerRecord onSend(ProducerRecord producerRecord) {
        return new ProducerRecord(producerRecord.topic(), producerRecord.partition(), producerRecord.timestamp(),
                producerRecord.key(), System.currentTimeMillis() + ":" + producerRecord.value().toString());
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    @Override
    public void close() {

    }
}
