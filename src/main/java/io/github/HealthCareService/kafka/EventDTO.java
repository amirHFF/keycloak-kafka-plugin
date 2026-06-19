package io.github.HealthCareService.kafka;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 6/16/2026 - 12:50 AM
*/

import org.keycloak.events.EventType;

public class EventDTO {
  private String uuid;
  private long occurredAt;
  private EventType eventType;
  private String keycloakId;
  private String clientId;
  private String realmId;

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getRealmId() {
    return realmId;
  }

  public void setRealmId(String realmId) {
    this.realmId = realmId;
  }

  public String getKeycloakId() {
    return keycloakId;
  }

  public void setKeycloakId(String keycloakId) {
    this.keycloakId = keycloakId;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public long getOccurredAt() {
    return occurredAt;
  }

  public void setOccurredAt(long occurredAt) {
    this.occurredAt = occurredAt;
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }
}

