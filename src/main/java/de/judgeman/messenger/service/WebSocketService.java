package de.judgeman.messenger.service;

import de.judgeman.messenger.component.WebSocketEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Paul Richter on Mon 10/04/2023
 */
@Service
public class WebSocketService {

    @Autowired
    private WebSocketEventListener webSocketEventListener;

    public int getActiveConnections() {
        return webSocketEventListener.getActiveConnections();
    }
}
