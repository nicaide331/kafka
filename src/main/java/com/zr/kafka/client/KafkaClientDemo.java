package com.zr.kafka.client;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Arrays;
import java.util.Properties;

import static com.zr.kafka.util.PropertiesUtil.BROKER_URL;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年12月04日 17:09:00
 */
public class KafkaClientDemo {
    public static void main(String[] args) {

        //创建主题
        ClientUtil.createTopic("demo",3, (short) 3);

        //获取主题
        DescribeTopicsResult result = ClientUtil.getTopicInfo("demo");

        //删除主题
        ClientUtil.deleteTopic("demo");

        //关闭连接
        ClientUtil.closeClient();


    }
}
