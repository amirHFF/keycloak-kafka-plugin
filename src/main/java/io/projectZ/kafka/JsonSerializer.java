package io.projectZ.kafka;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 6/16/2026 - 8:16 AM
*/

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class JsonSerializer<T> implements Serializer<T> {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public byte[] serialize(String topic, T event) {
        try {
            return objectMapper.writeValueAsBytes(event);
        } catch (JsonProcessingException e) {
            System.out.println("parsing topic exception :"+topic);
            System.out.println("exception : "+e);

            throw new RuntimeException(e);
        }
    }
}

