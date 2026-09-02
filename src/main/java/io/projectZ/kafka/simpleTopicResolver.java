package io.projectZ.kafka;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 8/31/2026 - 12:48 PM
*/

import io.projectZ.Properties;
import io.projectZ.kafka.dto.AdminEventDto;
import io.projectZ.kafka.dto.EventDTO;
import io.projectZ.kafka.dto.UserEventDto;
import org.jboss.logging.Logger;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class simpleTopicResolver implements TopicResolver {
    private final Logger logger = Logger.getLogger(simpleTopicResolver.class);
    private final List<EventType> allowedUserEventTypes = new ArrayList<>(List.of(EventType.REGISTER, EventType.UPDATE_PROFILE,
            EventType.DELETE_ACCOUNT));
    private final List<ResourceType> resourceTypes = new ArrayList<>(List.of(ResourceType.USER, ResourceType.USER_PROFILE));

    @Override
    public String resolve(EventDTO eventDTO) {
        if (eventDTO instanceof UserEventDto userEvent) {
            logger.info("user event detected, eventType:"+userEvent.getEventType().name());
            if (allowedUserEventTypes.contains(userEvent.getEventType())) {
                logger.info("topic is user: "+Properties.KAFKA_TOPIC);
                return "user-sync-events";
            }
        }
        if (eventDTO instanceof AdminEventDto adminEvent) {
            logger.info("admin event detected, eventType:"+adminEvent.getResourceType().name());
            if (resourceTypes.contains(adminEvent.getResourceType()))
                logger.info("topic is admin: "+Properties.KAFKA_TOPIC);
                return "user-sync-events";
        }
        logger.info("no topic resolved for "+  eventDTO.getId());
        return null;
    }

}

