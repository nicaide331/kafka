package com.zr.kafka.demo;


import com.zr.kafka.util.ConnectionInfo;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.RetriableException;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月28日 10:26:00
 */
public class ProducerTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = ConnectionInfo.getProperties();

        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i = 0; i < 1000; i++) {
             ProducerRecord<String, String> record = new ProducerRecord<String, String>("test", String.valueOf(i));
//             producer.send(record).get();
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if(e == null){
                        System.out.println("success");
                    } else {
                        if (e instanceof RetriableException) {
                            //处理可重试瞬间异常

                        }else {
                            //处理不可重试异常
                        }
                        System.out.println("error");

                    }
                }
            });
        }
        producer.close();
    }
}
