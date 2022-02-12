package com.antonr.webserver.service;

import com.antonr.webserver.exception.BadRequestException;
import com.antonr.webserver.exception.InternalServerErrorException;
import com.antonr.webserver.exception.NotFoundException;
import com.antonr.webserver.model.HttpCode;
import com.antonr.webserver.model.Request;
import com.antonr.webserver.model.Response;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class RequestHandler {

  private static final Logger LOGGER;

  static {
    LOGGER = Logger.getLogger("com.antonr.webserver.service.RequestHandler");
    Handler consoleHandler = new ConsoleHandler();
    consoleHandler.setLevel(Level.ALL);
    LOGGER.addHandler(consoleHandler);
    LOGGER.setLevel(Level.ALL);
    LOGGER.setUseParentHandlers(false);
  }

  private final BufferedReader reader;
  private final BufferedWriter writer;
  private final ContentReader contentReader;

  public void handle() {
    try {
      Request request = new RequestParser().parseRequest(reader);
      Response response = new Response(contentReader.readContent(request.getUri()), HttpCode.OK,
          new HashMap<>());
      new ResponseWriter().writeResponse(writer, response);
    } catch (BadRequestException e) {
      LOGGER.log(Level.WARNING, HttpCode.OK.toString());
    } catch (NotFoundException e) {
      LOGGER.log(Level.WARNING, HttpCode.NOT_FOUND.toString());
    } catch (InternalServerErrorException e) {
      LOGGER.log(Level.WARNING, HttpCode.INTERNAL_SERVER_ERROR.toString());
    }
  }
}
