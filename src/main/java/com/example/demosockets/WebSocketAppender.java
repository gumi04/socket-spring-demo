package com.example.demosockets;

import java.nio.charset.StandardCharsets;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebSocketAppender extends AppenderBase<ILoggingEvent> {
  // encoder is required. And it has to have legal getter/setter methods.
  private PatternLayoutEncoder encoder;

  @Override
  protected void append(ILoggingEvent eventObject) {

    // Use encoder to encode logs.
    byte[] data = this.encoder.encode(eventObject);

    // Push to client.
    LogChannel.push(new String(data, StandardCharsets.UTF_8));
  }
}
