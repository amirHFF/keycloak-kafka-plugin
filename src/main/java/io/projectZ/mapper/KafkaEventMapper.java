package io.projectZ.mapper;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 7/14/2026 - 10:21 AM
*/

import io.projectZ.kafka.dto.EventDTO;
import org.keycloak.events.Event;

public interface KafkaEventMapper<D , E> {

    D map(E event);

}

