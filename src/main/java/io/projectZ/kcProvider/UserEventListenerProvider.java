package io.projectZ.kcProvider;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 6/15/2026 - 10:20 PM
*/

import io.projectZ.kafka.AdminEventDto;
import io.projectZ.kafka.EventDTO;
import io.projectZ.kafka.Publisher;
import io.projectZ.kafka.UserEventDto;
import org.jboss.logging.Logger;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

public class UserEventListenerProvider implements EventListenerProvider {
    private final Publisher publisher;
    private final Logger logger = Logger.getLogger(UserEventListenerProvider.class);

    public UserEventListenerProvider() {
        publisher = Publisher.getInstance();
    }

    @Override
    public void onEvent(Event event) {

        EventDTO eventDTO = createEventDto(event);
        publisher.publish(eventDTO);
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        EventDTO eventDTO = createAdminEventDto(adminEvent);
        publisher.publish(eventDTO);

    }

    @Override
    public void close() {
        publisher.close();
    }

    private UserEventDto createEventDto(Event event) {
        if (event != null) {
            UserEventDto eventDTO = new UserEventDto();

            eventDTO.setDetails(event.getDetails());
            eventDTO.setEventType(event.getType());
            eventDTO.setId(event.getId());
            eventDTO.setOccurredAt(event.getTime());
            eventDTO.setClientId(event.getClientId());
            eventDTO.setRealmId(event.getRealmId());
            return eventDTO;
        } else {
            logger.error("event is empty");
            throw new IllegalArgumentException("event is empty");
        }
    }
    private AdminEventDto createAdminEventDto(AdminEvent event) {
        if (event != null) {
            AdminEventDto eventDTO = new AdminEventDto();

            eventDTO.setResourceId(event.getResourceId());
            eventDTO.setId(event.getId());
            eventDTO.setOccurredAt(event.getTime());
            eventDTO.setResourceType(event.getResourceTypeAsString());
            eventDTO.setRealmId(event.getRealmId());
            eventDTO.setOperationType(event.getOperationType().name());
            eventDTO.setAuthDetails(event.getAuthDetails());
            return eventDTO;
        } else {
            logger.error("event is empty");
            throw new IllegalArgumentException("event is empty");
        }
    }
}

