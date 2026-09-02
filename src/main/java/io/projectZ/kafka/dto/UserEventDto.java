package io.projectZ.kafka.dto;
/*
    Project : keycloak-kafka-Listener
    Author  : a.FouladiFar
    Created : 08/07/2026
*/

import java.util.Map;
import org.keycloak.events.EventType;

public class UserEventDto extends EventDTO{
    public UserEventDto() {
        userEvent = true;
    }

    private String userid;
    private EventType eventType;
    private String clientId;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
