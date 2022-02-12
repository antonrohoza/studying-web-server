package com.antonr.webserver.service;

import static com.antonr.webserver.Constants.HEADER;
import static com.antonr.webserver.Constants.HEADERS_SEPARATOR;
import static com.antonr.webserver.Constants.LINE_SEPARATOR;
import static com.antonr.webserver.Constants.WHITE_SPACE;

import com.antonr.webserver.exception.InternalServerErrorException;
import com.antonr.webserver.model.HttpCode;
import com.antonr.webserver.model.Response;
import java.io.IOException;
import java.io.Writer;

public final class ResponseWriter {

  public void writeResponse(Writer writer, Response response) throws InternalServerErrorException {
    try {
      writeHeader(writer, response.getHttpCode());
      String line = response.getContent().readLine();
      while (line != null) {
        writer.write(line);
        line = response.getContent().readLine();
      }
    } catch (IOException e) {
      throw new InternalServerErrorException(e.getMessage());
    }
  }

  public void writeHeader(Writer writer, HttpCode httpStatus) throws InternalServerErrorException {
    String line = HEADER
        + httpStatus.getCode() + HEADERS_SEPARATOR + WHITE_SPACE
        + httpStatus.getMessage()
        + LINE_SEPARATOR + LINE_SEPARATOR;
    try {
      writer.write(line);
    } catch (IOException e) {
      throw new InternalServerErrorException(e.getMessage());
    }
  }
}
