package com.example.demosockets;

import com.google.gson.Gson;
import org.apache.logging.log4j.message.Message;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.lang.reflect.Type;

public class WatchCalendarModelDecoder implements Decoder.Text<WatchCalendarModel>{
  private static Gson gson = new Gson();
  @Override
  public WatchCalendarModel decode(String s) throws DecodeException {
    WatchCalendarModel message = gson.fromJson(s, WatchCalendarModel.class);
    return message;
  }

  @Override
  public boolean willDecode(String s) {
    return (s != null);
  }

  @Override
  public void init(EndpointConfig endpointConfig) {

  }

  @Override
  public void destroy() {

  }
}
