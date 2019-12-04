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
 * @date 2019年12月04日 17:26:00
 */
public class ClientUtil {

    private static AdminClient client;

    public static AdminClient connectClient() {
        Properties properties = new Properties();
        properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, BROKER_URL);
        client = AdminClient.create(properties);
        return client;
    }

    public static void closeClient() {
        client.close();
        System.out.println("Client关闭");

    }

    /**
     * 创建主题
     * @param topicName
     * @param numPartition
     * @param replicationFactor
     * @return void
     * @author nicaide
     * @date 2019/12/4 0004 17:43
     */
    public static void createTopic(String topicName, int  numPartition, short replicationFactor){
        AdminClient client = ClientUtil.connectClient();
        NewTopic newTopic = new NewTopic(topicName, numPartition, replicationFactor);
        System.out.println("主题对象实例化成功");
        client.createTopics(Arrays.asList(newTopic));
        System.out.println("主题创建成功");
    }

    public static DescribeTopicsResult getTopicInfo(String topicName) {
        AdminClient client = ClientUtil.connectClient();
        return client.describeTopics(Arrays.asList(topicName));
    }

    /**
     * 删除主题
     * @param topicName
     * @return void
     * @author nicaide
     * @date 2019/12/4 0004 17:43
     */
    public static void deleteTopic(String topicName) {
        AdminClient client = ClientUtil.connectClient();
        System.out.println("准备删除主题:" + topicName);
        client.deleteTopics(Arrays.asList(topicName));
        System.out.println(topicName + "删除成功");
    }
}
