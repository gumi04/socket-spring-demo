package com.example.demosockets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.valueOf;

@RestController
@RequestMapping("/watch")
@Slf4j
@CrossOrigin
public class Controller {
  private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
  public static final String OK = "OK";

  @PostMapping(value = "/calendar")
  public final Object calendar(final @RequestHeader("x-goog-channel-id") String email) {
    try {
      WatchCalendarModel response = new WatchCalendarModel();
      response.setTipo("calendario");
      response.setEmail("emailDecodificado");
      ObjectMapper mapper = new ObjectMapper();
      LogChannel.push(mapper.writeValueAsString(response));
      return response;
    } catch (Exception e) {
      LOGGER.info("Error en mensaje de watch", e.getMessage());
      return null;
    }
  }
}
