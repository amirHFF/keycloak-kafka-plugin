package io.projectZ.kafka.dto;
/*
    Project : keycloak-kafka-Listener
    Author  : a.FouladiFar
    Created : 08/07/2026
*/

import org.keycloak.events.admin.AuthDetails;
import org.keycloak.events.admin.OperationType;
import org.keycloak.events.admin.ResourceType;

public class AdminEventDto extends EventDTO{

    public AdminEventDto() {
        userEvent = false;
    }

    private ResourceType resourceType;
    private String resourceId;
    private OperationType operationType;
    private AuthDetails authDetails;

    public AuthDetails getAuthDetails() {
        return authDetails;
    }

    public void setAuthDetails(AuthDetails authDetails) {
        this.authDetails = authDetails;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
