package io.projectZ.kcProvider;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 6/15/2026 - 10:20 PM
*/

import io.projectZ.kafka.dto.AdminEventDto;
import io.projectZ.kafka.dto.EventDTO;
import io.projectZ.kafka.Publisher;
import io.projectZ.kafka.dto.UserEventDto;
import io.projectZ.mapper.AdminEventMapper;
import io.projectZ.mapper.KafkaEventMapper;
import io.projectZ.mapper.UserEventMapper;
import org.jboss.logging.Logger;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

public class UserEventListenerProvider implements EventListenerProvider {
    private final Publisher publisher;
    private final Logger logger = Logger.getLogger(UserEventListenerProvider.class);
    private KafkaEventMapper<AdminEventDto , AdminEvent> adminEventMapper = new AdminEventMapper();
    private KafkaEventMapper<UserEventDto , Event> userEventMapper = new UserEventMapper();

    public UserEventListenerProvider() {
        publisher = Publisher.getInstance();
    }

    @Override
    public void onEvent(Event event) {
        logger.info("user event triggered");
        UserEventDto userEventDto = userEventMapper.map(event);
        publisher.publish(userEventDto);
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        logger.info("admin event triggered");
        AdminEventDto event = adminEventMapper.map(adminEvent);
        publisher.publish(event);

    }

    @Override
    public void close() {
        publisher.close();
    }

}

