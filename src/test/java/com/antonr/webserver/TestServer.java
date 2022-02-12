package com.antonr.webserver;

import java.io.IOException;
import org.junit.jupiter.api.Test;

public class TestServer {

  @Test
  public void test() throws IOException {
    Server server = new Server(3000, "src/main/resources");
    server.start();
  }

}
