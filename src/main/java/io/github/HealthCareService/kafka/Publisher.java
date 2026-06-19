package io.github.HealthCareService.kafka;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 6/15/2026 - 1:07 AM
*/

import io.github.HealthCareService.Main;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jboss.logging.Logger;

import java.util.Properties;

public class Publisher {
    private Logger logger = Logger.getLogger(Publisher.class);
    private final String topic;
    Properties props = new Properties();
    KafkaProducer<String, EventDTO> kafkaProducer;

    public Publisher(String topic) {
        this.topic = topic;
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, io.github.HealthCareService.Properties.KAFKA_ADDRESS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        props.put(ProducerConfig.LINGER_MS_CONFIG, 200);
        props.put(ProducerConfig.RETRIES_CONFIG, 100);

        kafkaProducer = new KafkaProducer<>(props);
        logger.info("kafka initialized ...");
    }

    public void publish(EventDTO eventDTO) {

        logger.info("publish for " + eventDTO.getKeycloakId() );
        ProducerRecord<String, EventDTO> record = new ProducerRecord<>(topic, eventDTO.getKeycloakId(), eventDTO);
        kafkaProducer.send(record, (recordMetadata, e) -> {
            logger.info("event timestamp : " + recordMetadata.timestamp() + " offset :"+ recordMetadata.offset());
            if (e != null)
                logger.error("exception : " + e);
        });
        kafkaProducer.flush();
    }

    public void close() {
        kafkaProducer.close();
    }
}

