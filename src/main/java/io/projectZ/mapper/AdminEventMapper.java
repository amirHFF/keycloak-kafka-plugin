package io.projectZ.mapper;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 7/14/2026 - 10:24 AM
*/

import io.projectZ.kafka.dto.AdminEventDto;
import io.projectZ.kafka.dto.UserInfo;
import io.projectZ.kcProvider.UserEventListenerProvider;
import org.jboss.logging.Logger;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.OperationType;
import org.keycloak.events.admin.ResourceType;

public class AdminEventMapper implements KafkaEventMapper<AdminEventDto, AdminEvent> {
    private final Logger logger = Logger.getLogger(UserEventListenerProvider.class);

    @Override
    public AdminEventDto map(AdminEvent event) {
        if (event != null) {
            AdminEventDto eventDTO = new AdminEventDto();

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

            eventDTO.setResourceId(event.getResourceId());
            eventDTO.setId(event.getId());
            eventDTO.setOccurredAt(event.getTime());
            eventDTO.setResourceType(event.getResourceType());
            eventDTO.setRealmId(event.getRealmId());
            eventDTO.setOperationType(event.getOperationType());
            eventDTO.setAuthDetails(event.getAuthDetails());
            return eventDTO;
        } else {
            logger.error("event is empty");
            throw new IllegalArgumentException("event is empty");
        }
    }

}

