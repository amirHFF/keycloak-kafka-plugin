package io.projectZ.kafka.dto;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 6/16/2026 - 12:50 AM
*/

import java.util.Map;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AuthDetails;

public class EventDTO {
  private String id;
  private long occurredAt;
  private String realmId;

  private Map<String , String> details;

  private UserInfo userInfo;

  public String getRealmId() {
    return realmId;
  }

  public void setRealmId(String realmId) {
    this.realmId = realmId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getOccurredAt() {
    return occurredAt;
  }

  public void setOccurredAt(long occurredAt) {
    this.occurredAt = occurredAt;
  }

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  public Map<String, String> getDetails() {
    return details;
  }

  public void setDetails(Map<String, String> details) {
    this.details = details;
  }
}

