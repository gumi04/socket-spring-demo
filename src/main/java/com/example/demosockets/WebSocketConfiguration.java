package com.example.demosockets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfiguration {
  @Bean
  public ServerEndpointExporter serverEndpointExporter() {
    ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();

    /**
     * Add one or more classes annotated with `@ServerEndpoint`.
     */
    serverEndpointExporter.setAnnotatedEndpointClasses(LogChannel.class);

    return serverEndpointExporter;
  }
}
