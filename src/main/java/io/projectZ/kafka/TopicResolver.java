package io.projectZ.kafka;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 8/31/2026 - 12:46 PM
*/

import io.projectZ.kafka.dto.EventDTO;

public interface TopicResolver {
    String resolve(EventDTO eventDTO);
}
