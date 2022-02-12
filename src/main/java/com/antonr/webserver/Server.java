package com.antonr.webserver;

import com.antonr.webserver.service.ContentReader;
import com.antonr.webserver.service.RequestHandler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Server {

  private final int port;
  private final String webAppPath;

  public void start() throws IOException {
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      while (true) {
        try (Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()))) {
          ContentReader contentReader = new ContentReader(webAppPath);
          RequestHandler handler = new RequestHandler(reader, writer, contentReader);
          handler.handle();
        }
      }
    }
  }
}
