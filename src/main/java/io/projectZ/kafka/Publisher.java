package io.projectZ.kafka;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 6/15/2026 - 1:07 AM
*/

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jboss.logging.Logger;

public class Publisher {

    private static Publisher instance = new Publisher();
    private final Logger logger = Logger.getLogger(Publisher.class);
    private final String topic;
    private final Properties props = new Properties();
    private final KafkaProducer<String, EventDTO> kafkaProducer;

    private Publisher() {
        this.topic = io.projectZ.Properties.KAFKA_TOPIC;
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, io.projectZ.Properties.KAFKA_ADDRESS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        props.put(ProducerConfig.LINGER_MS_CONFIG, 200);
        props.put(ProducerConfig.RETRIES_CONFIG, 100);
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        kafkaProducer = new KafkaProducer<>(props);
        logger.info("kafka initialized ...");
    }

    public static Publisher getInstance() {
        return instance;
    }

    public void publish(EventDTO eventDTO) {

        logger.info("publish for " + eventDTO.getId());
        ProducerRecord<String, EventDTO> record = new ProducerRecord<>(topic, eventDTO.getId(), eventDTO);
        kafkaProducer.send(record, (recordMetadata, e) -> {
            logger.info("event timestamp : " + recordMetadata.timestamp() + " offset :" + recordMetadata.offset());
            if (e != null) {
                logger.error("exception : " + e);
            }
        });
    }

    public void close() {
        kafkaProducer.flush();
        kafkaProducer.close();
    }
}

