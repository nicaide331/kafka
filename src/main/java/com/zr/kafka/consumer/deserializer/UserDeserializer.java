package com.zr.kafka.consumer.deserializer;

import com.zr.kafka.produce.serializer.User;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * 描述
 *
 * @author nicaide
 * @date 2019年11月29日 14:56:00
 */
public class UserDeserializer implements Deserializer {

    private ObjectMapper objectMapper;

    @Override
    public Object deserialize(String s, byte[] data) {
        User user = null;
        try {
            user = objectMapper.readValue(data, User.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return user;
        }
    }

    @Override
    public void configure(Map configs, boolean isKey) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public void close() {

    }
}
