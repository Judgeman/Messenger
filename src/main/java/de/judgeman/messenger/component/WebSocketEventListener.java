package de.judgeman.messenger.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Created by Paul Richter on Sun 09/04/2023
 */
@Component
public class WebSocketEventListener {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    int activeSessions = 0;

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        activeSessions++;
        logger.info("New session registered (" + activeSessions + ")");
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        activeSessions--;
        logger.info("Session unregistered (" + activeSessions + " left)");
    }

    public int getActiveConnections() {
        return activeSessions;
    }
}
