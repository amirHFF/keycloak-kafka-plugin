package io.projectZ.kcProvider;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 6/15/2026 - 10:20 PM
*/

import io.projectZ.kafka.TopicResolver;
import io.projectZ.kafka.dto.AdminEventDto;
import io.projectZ.kafka.dto.EventDTO;
import io.projectZ.kafka.Publisher;
import io.projectZ.kafka.dto.UserEventDto;
import io.projectZ.kafka.simpleTopicResolver;
import io.projectZ.mapper.AdminEventMapper;
import io.projectZ.mapper.KafkaEventMapper;
import io.projectZ.mapper.UserEventMapper;
import org.jboss.logging.Logger;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

public class UserEventListenerProvider implements EventListenerProvider {
    private final Publisher publisher;
    private final TopicResolver topicResolver = new simpleTopicResolver();
    private final Logger logger = Logger.getLogger(UserEventListenerProvider.class);
    private KafkaEventMapper<AdminEventDto , AdminEvent> adminEventMapper = new AdminEventMapper();
    private KafkaEventMapper<UserEventDto , Event> userEventMapper = new UserEventMapper();

    public UserEventListenerProvider() {
        publisher = Publisher.getInstance();
    }

    @Override
    public void onEvent(Event event) {
        logger.info("user event triggered event :"+event.getUserId());
        UserEventDto userEventDto = userEventMapper.map(event);
        publisher.publish(topicResolver.resolve(userEventDto), userEventDto);
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        logger.info("admin event triggered ,resourceType : "+adminEvent.getResourceType().name());
        AdminEventDto event = adminEventMapper.map(adminEvent);
        publisher.publish(topicResolver.resolve(event), event);

    }

    @Override
    public void close() {
        publisher.close();
    }

}

