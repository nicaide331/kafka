package com.zr.kafka.produce.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月28日 18:05:00
 */
public class Counterinterceptor implements ProducerInterceptor<String, String> {

    private int errorCount = 0;
    private int successCount = 0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e == null) {
            successCount++;
        }else {
            errorCount++;
        }
    }

    @Override
    public void close() {
        System.out.println("Successful sent" + successCount);
        System.out.println("Error sent" + errorCount);
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
