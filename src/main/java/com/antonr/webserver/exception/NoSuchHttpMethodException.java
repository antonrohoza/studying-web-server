package com.antonr.webserver.exception;

public class NoSuchHttpMethodException extends RuntimeException {

  public NoSuchHttpMethodException(String str) {
    super(str);
  }
}
