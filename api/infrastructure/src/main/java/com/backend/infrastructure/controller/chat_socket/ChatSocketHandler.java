package com.backend.infrastructure.controller.chat_socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ChatSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(ChatSocketHandler.class);

    @OnConnect
    public void onConnect(SocketIOClient client) {
        logger.info("Client connected: {}", client.getSessionId());
        logger.debug("Client handshake data: {}", client.getHandshakeData());
        logger.debug("Client remote address: {}", client.getRemoteAddress());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        logger.info("Client disconnected: {}", client.getSessionId());
        logger.debug("Client remote address: {}", client.getRemoteAddress());
        System.out.println("Client disconnected: " + client.getSessionId());
    }

}

