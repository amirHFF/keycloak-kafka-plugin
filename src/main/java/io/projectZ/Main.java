package io.projectZ;

import io.projectZ.kafka.Publisher;

import io.projectZ.kafka.TopicResolver;
import io.projectZ.kafka.dto.UserEventDto;
import io.projectZ.kafka.simpleTopicResolver;
import org.keycloak.events.EventType;

import java.util.Properties;

public class Main {
    public static final String KAFKA_ADDRESS_STRING = "KAFKA_ADDRESS";
    public static final String KAFKA_TOPIC_STRING = "TOPIC";
    public static final String KEY_CLOAK_PROVIDER_ID_STRING = "KEY_CLOAK_PROVIDER_ID";
    public static Properties properties = new Properties();

    public static void main(String[] args) {
//        for (int i = 0; i < args.length; i++) {
//            switch (i) {
//                case 0:
//                    io.projectZ.Properties.KAFKA_ADDRESS=args[i];
//                    break;
//                case 1:
//                    properties.put(KAFKA_TOPIC_STRING, args[i]);
//                    break;
//                case 2:
//                    properties.put(KEY_CLOAK_PROVIDER_ID_STRING, args[i]);
//                    break;
//            }
//        }
//        Publisher publisher = Publisher.getInstance();
        UserEventDto eventDTO = new UserEventDto();
        eventDTO.setRealmId("xx");
        eventDTO.setClientId("11");
        eventDTO.setId("uu11");
        eventDTO.setId("keycloak11");
        eventDTO.setEventType(EventType.REGISTER);
        TopicResolver resolver = new simpleTopicResolver();
        String x = resolver.resolve(eventDTO);
        System.out.println(x);
    }
}