package com.antonr.webserver.service;

import com.antonr.webserver.exception.NotFoundException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class ContentReader {

  private final String webAppPath;

  public BufferedReader readContent(String uri) throws NotFoundException {
    try {
      return new BufferedReader(new FileReader(webAppPath + uri));
    } catch (FileNotFoundException e) {
      throw new NotFoundException(e.getMessage());
    }
  }
}
