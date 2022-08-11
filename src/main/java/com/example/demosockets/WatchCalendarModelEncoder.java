package com.example.demosockets;

import com.google.gson.Gson;
import org.apache.logging.log4j.message.Message;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class WatchCalendarModelEncoder implements Encoder.Text<WatchCalendarModel>{

  private static Gson gson = new Gson();

  @Override
  public String encode(WatchCalendarModel object) throws EncodeException {
    return gson.toJson(object);
  }

  @Override
  public void init(EndpointConfig endpointConfig) {

  }

  @Override
  public void destroy() {

  }
}
