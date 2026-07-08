package io.projectZ.kafka;
/*
    Project : keycloak-kafka-Listener
    Author  : a.FouladiFar
    Created : 08/07/2026
*/

import java.util.Map;
import org.keycloak.events.EventType;

public class UserEventDto extends EventDTO{
    private EventType eventType;
    private String clientId;
    private Map<String , String> details;

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

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
}
