package com.backend.infrastructure.controller.config.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
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
        socketIOServer.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("Client connected: " + client.getSessionId());
            }
        });

        socketIOServer.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                System.out.println("Client disconnected: " + client.getSessionId());
            }
        });
        socketIOServer.start();
    }

    @PreDestroy
    public void stopServer() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            System.out.println("Socket.IO server stopped.");
        }
    }
}
