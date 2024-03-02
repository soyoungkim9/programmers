package main.java.project;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class BasicPathHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange path) throws IOException {
      String response = """ 
              { "message" :"server check" } 
              """;
      path.getResponseHeaders().add("Content-Type", "application/json");
      path.sendResponseHeaders(200, response.getBytes().length);
      OutputStream os = path.getResponseBody();
      os.write(response.getBytes());
      os.close();
  }
}
