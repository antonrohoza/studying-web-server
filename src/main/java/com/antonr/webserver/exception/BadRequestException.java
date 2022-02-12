package com.antonr.webserver.exception;

public class BadRequestException extends Exception {

  public BadRequestException(String str) {
    super(str);
  }

}
