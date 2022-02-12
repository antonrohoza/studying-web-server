package com.antonr.webserver.model;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {

  private String uri;
  private HttpMethod httpMethod;
  private Map<String, String[]> headers;
}
