package com.antonr.webserver.exception;

public class NoHeaderException extends RuntimeException {

  public NoHeaderException(String str) {
    super(str);
  }
}
