package io.github.HealthCareService;

import io.github.HealthCareService.kafka.EventDTO;
import io.github.HealthCareService.kafka.Publisher;

import java.util.Properties;

public class Main {
    public static final String KAFKA_ADDRESS_STRING = "KAFKA_ADDRESS";
    public static final String KAFKA_TOPIC_STRING = "TOPIC";
    public static final String KEY_CLOAK_PROVIDER_ID_STRING = "KEY_CLOAK_PROVIDER_ID";
    public static Properties properties = new Properties();

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (i) {
                case 0:
                    io.github.HealthCareService.Properties.KAFKA_ADDRESS=args[i];
                    break;
                case 1:
                    properties.put(KAFKA_TOPIC_STRING, args[i]);
                    break;
                case 2:
                    properties.put(KEY_CLOAK_PROVIDER_ID_STRING, args[i]);
                    break;
            }
        }
        Publisher publisher = new Publisher("user-events");
        EventDTO eventDTO = new EventDTO();
        eventDTO.setRealmId("xx");
        eventDTO.setClientId("11");
        eventDTO.setUuid("uu11");
        eventDTO.setKeycloakId("keycloak11");
        publisher.publish(eventDTO);
    }
}