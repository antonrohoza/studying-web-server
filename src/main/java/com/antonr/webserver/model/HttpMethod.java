package com.antonr.webserver.model;

import com.antonr.webserver.exception.NoSuchHttpMethodException;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HttpMethod {
  GET("GET"),
  POST("POST");

  private final String value;

  public static HttpMethod getHttpMethod(String httpMethod) {
    return Arrays.stream(HttpMethod.values())
        .filter(method -> httpMethod.equals(method.getValue()))
        .findFirst()
        .orElseThrow(
            () -> new NoSuchHttpMethodException("There is no such HTTP method: " + httpMethod));
  }
}
