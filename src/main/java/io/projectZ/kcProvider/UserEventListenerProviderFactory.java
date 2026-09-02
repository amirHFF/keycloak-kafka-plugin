package io.projectZ.kcProvider;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 6/15/2026 - 10:35 PM
*/

import io.projectZ.Properties;
import org.jboss.logging.Logger;
import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class UserEventListenerProviderFactory implements EventListenerProviderFactory {
    private EventListenerProvider provider;
    private Logger logger = Logger.getLogger(UserEventListenerProviderFactory.class);

    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {
        provider = new UserEventListenerProvider();
        logger.info("provider creation ...");
        return provider;
    }

    @Override
    public void init(Config.Scope scope) {

        logger.info("factory init ...");
        Properties.KAFKA_ADDRESS = scope.get("address");
        Properties.KAFKA_TOPIC = scope.get("topic");
        logger.info(Properties.KAFKA_ADDRESS);
        logger.info(Properties.KAFKA_TOPIC);

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {
        if(provider !=null) {
            provider.close();
        }
    }

    @Override
    public String getId() {
        return "user-sync-provider";
    }
}

