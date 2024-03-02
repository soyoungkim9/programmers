package main.java.project;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
  public static void main(String[] args) throws IOException {
    int port = 5678;
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/", new BasicPathHandler());
    server.createContext("/sum", new SumPathHandler());
    server.setExecutor(null);
    server.start();
  }
}
