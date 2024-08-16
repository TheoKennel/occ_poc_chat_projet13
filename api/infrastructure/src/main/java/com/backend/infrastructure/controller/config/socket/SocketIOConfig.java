package com.backend.infrastructure.controller.config.socket;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfig {

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("127.0.0.25");
        config.setPort(8085);

        return new SocketIOServer(config);
    }
}
