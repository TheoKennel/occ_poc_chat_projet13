package com.backend.infrastructure.controller.config.socket;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class SocketIOInitializer {

    private final SocketIOServer socketIOServer;

    @Autowired
    public SocketIOInitializer(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }

    @PostConstruct
    public void startServer() {
        socketIOServer.addConnectListener(client -> System.out.println("Client connected: " + client.getSessionId()));

        socketIOServer.addDisconnectListener(client -> System.out.println("Client disconnected: " + client.getSessionId()));
        socketIOServer.start();
    }

    public SocketIOServer getSocket() {
        return this.socketIOServer;
    }

    @PreDestroy
    public void stopServer() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            System.out.println("Socket.IO server stopped.");
        }
    }
}
