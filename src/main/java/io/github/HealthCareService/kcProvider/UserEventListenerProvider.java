package io.github.HealthCareService.kcProvider;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 6/15/2026 - 10:20 PM
*/

import io.github.HealthCareService.Main;
import io.github.HealthCareService.Properties;
import io.github.HealthCareService.kafka.EventDTO;
import io.github.HealthCareService.kafka.Publisher;
import org.jboss.logging.Logger;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

import java.util.UUID;

public class UserEventListenerProvider implements EventListenerProvider {
    private Publisher publisher;
    private Logger logger = Logger.getLogger("UserEventListenerProvider");

    public UserEventListenerProvider() {
        publisher = new Publisher(Properties.KAFKA_TOPIC);
    }

    @Override
    public void onEvent(Event event) {

        EventDTO eventDTO = createEventDto(event);
        publisher.publish(eventDTO);
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {


    }

    @Override
    public void close() {
        publisher.close();
    }

    private EventDTO createEventDto(Event event) {
        if (event != null) {
            EventDTO eventDTO = new EventDTO();

            eventDTO.setEventType(event.getType());
            eventDTO.setUuid(UUID.randomUUID().toString());
            eventDTO.setOccurredAt(System.currentTimeMillis());
            eventDTO.setKeycloakId(event.getId());
            eventDTO.setClientId(event.getClientId());
            eventDTO.setRealmId(event.getRealmId());
            return eventDTO;
        } else {
            logger.error("event is empty");
            throw new NullPointerException("event is empty");
        }
    }
}

