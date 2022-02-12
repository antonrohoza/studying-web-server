package com.antonr.webserver.service;

import static com.antonr.webserver.Constants.HEADER_VALUE_SEPARATOR;
import static com.antonr.webserver.Constants.WHITE_SPACE;

import com.antonr.webserver.exception.BadRequestException;
import com.antonr.webserver.exception.InternalServerErrorException;
import com.antonr.webserver.model.HttpMethod;
import com.antonr.webserver.model.Request;
import com.antonr.webserver.model.RequestElementSearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RequestParser {

  public Request parseRequest(BufferedReader reader)
      throws InternalServerErrorException, BadRequestException {
    Request request = new Request();
    try {
      injectUriAndHttpMethod(reader, request);
      injectHeaders(reader, request);
    } catch (IOException e) {
      throw new InternalServerErrorException(e.getMessage());
    }
    return request;
  }

  private void injectUriAndHttpMethod(BufferedReader reader, Request request)
      throws IOException, BadRequestException {
    String line = reader.readLine();
    request.setUri(getElementByRegexp(line, RequestElementSearch.URI));
    HttpMethod httpMethod = HttpMethod
        .getHttpMethod(getElementByRegexp(line, RequestElementSearch.HTTP_METHOD));
    request.setHttpMethod(httpMethod);
  }

  private void injectHeaders(BufferedReader reader, Request request)
      throws IOException, BadRequestException {
    HashMap<String, String[]> headers = new HashMap<>();
    String line;
    while (!(line = reader.readLine()).isEmpty()) {
      headers.put(getElementByRegexp(line, RequestElementSearch.HEADER),
          getElementByRegexp(line, RequestElementSearch.HEADER_VALUES)
              .split(HEADER_VALUE_SEPARATOR + WHITE_SPACE));
    }
    request.setHeaders(headers);
  }

  private static String getElementByRegexp(String line, RequestElementSearch requestElementSearch)
      throws BadRequestException {
    Optional<String> maybeElement = parseStrByRegexp(line, requestElementSearch.getRegexp());
    if (maybeElement.isEmpty()) {
      throw requestElementSearch.getException();
    }
    return maybeElement.get();
  }

  private static Optional<String> parseStrByRegexp(String line, String regexp) {
    Matcher matcher = Pattern.compile(regexp).matcher(line);
    return matcher.find() ? Optional.of(line.substring(matcher.start(), matcher.end()))
        : Optional.empty();
  }
}
