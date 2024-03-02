package main.java.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class SumPathHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange path) {
    // System.getProperty("user.dir") : 프로젝트 폴더의 주소를 가져옴
    String filePath = System.getProperty("user.dir") + "/API/data/input/user.json";
    // Java Object를 JSON으로 변환하기 위해 jackson 라이브러리 사용
    ObjectMapper objectMapper = new ObjectMapper();
    List<User> users = null;
    try {
      // 자바에서 File 상대경로의 기준은 해당 클래스 파일 위치가 아니라 클래스 파일이 포함되어 있는 프로젝트 폴더이다.
      // new File(System.getProperty("user.dir") + "/API/data/input/user.json") == new File("API/data/input/user.json")
      users = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
    } catch (Exception e) {
      e.printStackTrace();
    }

    int sum = 0;
    for(User user: users) {
      System.out.println(user.getPost_count());
      sum+= user.getPost_count();
    }

    String response = "{\"sum\":\"" + sum + "\"}";
    path.getResponseHeaders().add("Content-Type", "application/json");
    try {
      path.sendResponseHeaders(200, response.getBytes().length);
      OutputStream os = path.getResponseBody();
      os.write(response.getBytes());
      os.close();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
