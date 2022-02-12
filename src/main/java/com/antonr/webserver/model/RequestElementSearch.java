package com.antonr.webserver.model;

import static com.antonr.webserver.model.HttpCode.BAD_REQUEST;

import com.antonr.webserver.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RequestElementSearch {
  URI("/[[a-z]+/]*[a-z]+\\.[a-z]+",
      new BadRequestException(BAD_REQUEST.toString() + "There is no URI in current line")),
  HTTP_METHOD("[A-Z]+",
      new BadRequestException(
          BAD_REQUEST.toString() + "There is no HTTP method in current request")),
  HEADER("[\\w-]+:",
      new BadRequestException(BAD_REQUEST.toString() + "There is No Header in request")),
  HEADER_VALUES(" .+",
      new BadRequestException(
          BAD_REQUEST.toString() + "There is no Header values or they are inappropriate"));

  private final String regexp;
  private final BadRequestException exception;
}
