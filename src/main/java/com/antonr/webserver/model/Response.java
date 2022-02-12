package com.antonr.webserver.model;

import java.io.BufferedReader;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response {

  private final BufferedReader content;
  private final HttpCode httpCode;
  private final Map<String, String[]> headers;
}
