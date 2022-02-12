package com.antonr.webserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum HttpCode {
  OK(200, "OK"),
  BAD_REQUEST(400, "BAD_REQUEST"),
  NOT_FOUND(404, "NOT_FOUND"),
  INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR");

  private final int code;
  private final String message;
}
