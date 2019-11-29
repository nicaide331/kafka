package com.zr.kafka.produce.serializer;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月28日 17:09:00
 */
public class UserSerializer implements Serializer {

    private ObjectMapper objectMapper;
    private static Logger logger = LoggerFactory.getLogger(UserSerializer.class);

    @Override
    public byte[] serialize(String s, Object data) {
        byte[] ret = null;
        try {
            logger.warn("开始序列化");
            ret =    objectMapper.writeValueAsString(data).getBytes("utf-8");
            logger.warn("开始序结束");
        } catch (IOException e) {
            logger.warn("序列化错误");
        }
        return ret;
    }

    @Override
    public void configure(Map configs, boolean isKey) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Object data) {
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
