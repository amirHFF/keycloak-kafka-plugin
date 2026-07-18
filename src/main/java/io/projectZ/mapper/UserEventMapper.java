package io.projectZ.mapper;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 7/14/2026 - 10:24 AM
*/

import io.projectZ.kafka.dto.UserEventDto;
import io.projectZ.kafka.dto.UserInfo;
import io.projectZ.kcProvider.UserEventListenerProvider;
import org.jboss.logging.Logger;
import org.keycloak.events.Event;

public class UserEventMapper implements KafkaEventMapper<UserEventDto, Event> {
    private final Logger logger = Logger.getLogger(UserEventListenerProvider.class);

    @Override
    public UserEventDto map(Event event) {
        if (event != null) {
            UserEventDto eventDTO = new UserEventDto();

            if (event.getDetails() != null) {
                UserInfo userInfo = new UserInfo();
                eventDTO.setDetails(event.getDetails());
                if (event.getDetails().get("username") != null) {
                    userInfo.setUsername(event.getDetails().get("username"));
                    eventDTO.setUserInfo(userInfo);
                }
                if (event.getDetails().get("email") != null) {
                    userInfo.setUsername(event.getDetails().get("email"));
                    eventDTO.setUserInfo(userInfo);
                }
            }
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
}

