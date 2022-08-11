package com.example.demosockets;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@ServerEndpoint(value = "/channel/log")
@Slf4j
public class LogChannel {
  public static final ConcurrentMap<String, LogChannel> CHANNELS = new ConcurrentHashMap<>();

  private Session session;

  @OnMessage
  public void onMessage(String message) {

    log.debug("Recv Message: {}", message);
    push(message);
  }

  @OnOpen
  public void onOpen(Session session, EndpointConfig endpointConfig) {
    this.session = session;
    this.session.setMaxIdleTimeout(0);
    CHANNELS.put(this.session.getId(), this);

    log.info("New client connection: id={}", this.session.getId());
  }

  @OnClose
  public void onClose(CloseReason closeReason) {

    log.info("Connection disconnected: id={}, err={}", this.session.getId(), closeReason);

    CHANNELS.remove(this.session.getId());
  }

  @OnError
  public void onError(Throwable throwable) throws IOException {
    log.info("Connection Error: id={}, err={}", this.session.getId(), throwable);
    this.session.close(new CloseReason(CloseCodes.UNEXPECTED_CONDITION, throwable.getMessage()));
  }

  /**
   * Push messages to all clients
   *
   * @param message
   */
  public static void push(String message) {
    CHANNELS.values().stream().forEach(endpoint -> {
      synchronized (endpoint) {
        if (endpoint.session.isOpen()) {
            try {
              endpoint.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
        }
      }
    });
  }
}
