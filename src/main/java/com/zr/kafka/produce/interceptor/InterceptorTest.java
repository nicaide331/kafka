package com.zr.kafka.produce.interceptor;

import com.zr.kafka.util.ProduceConnectionInfo;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月28日 18:07:00
 */
public class InterceptorTest {
    public static void main(String[] args) {
        Properties properties = ProduceConnectionInfo.getProperties();

        //构建拦截链
        List<String> interceptors = new ArrayList<>();
        interceptors.add("com.zr.kafka.produce.interceptor.TimeStampPrependerInterceptor");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        String topic = "test";
        Producer<String, String> producer = new KafkaProducer<String, String>(properties);
        for (int i = 0; i < 100; i++) {
            ProducerRecord<String, String>  record = new ProducerRecord<>(topic, "message" + String.valueOf(i));
            try {
                producer.send(record).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        producer.close();
    }
}
