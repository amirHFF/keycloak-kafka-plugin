package io.projectZ.kafka;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 6/15/2026 - 1:07 AM
*/

import java.util.Properties;

import io.projectZ.kafka.dto.EventDTO;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jboss.logging.Logger;

public class Publisher {

    private static Publisher instance = new Publisher();
    private final Logger logger = Logger.getLogger(Publisher.class);
    private final Properties props = new Properties();
    private final KafkaProducer<String, EventDTO> kafkaProducer;

    private Publisher() {
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, io.projectZ.Properties.KAFKA_ADDRESS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        props.put(ProducerConfig.LINGER_MS_CONFIG, 200);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        kafkaProducer = new KafkaProducer<>(props);
        logger.info("kafka initialized ...");
    }

    public static Publisher getInstance() {
        return instance;
    }

    public void publish(String topic ,EventDTO eventDTO) {

        if (topic !=null) {
            try {
                logger.info("publish for :" + eventDTO.getId());
                logger.info("topic is :" + topic);
                ProducerRecord<String, EventDTO> record = new ProducerRecord<>(topic, eventDTO.getId(), eventDTO);
                kafkaProducer.send(record, (recordMetadata, exception) -> {
                    logger.info("event timestamp : " + recordMetadata.timestamp() + " offset :" + recordMetadata.offset());
                    if (exception != null) {
                        logger.error("exception : " + exception);
                    }
                });
            }catch (Exception e){
                logger.error("ignored exception : ",e);
            }
        }else {
            logger.info("ignored event ...");
        }
    }

    public void close() {
        kafkaProducer.flush();
    }
}

