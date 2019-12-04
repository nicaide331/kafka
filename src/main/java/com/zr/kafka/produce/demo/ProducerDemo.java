package com.zr.kafka.produce.demo;


import com.zr.kafka.util.ProduceConnectionInfo;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.RetriableException;

import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月28日 10:26:00
 */
public class ProducerDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = ProduceConnectionInfo.getProperties();

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        Scanner scanner = new Scanner(System.in);


         for (int i = 0; i < 10000; i++) {
             String input = scanner.next();
             ProducerRecord<String, String> record = new ProducerRecord<String, String>("test", input);
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
