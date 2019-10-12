package Server;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.sql.CallableStatement;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.simple.*;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HTTPDemo {

  public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);  // creates server
    server.createContext("/", new MyHandler());   // adds a basic handler (Needed implementation)
    server.setExecutor(null);    // creates a default executor
    server.start();
    System.out.println("__Server active__");

  }

  static class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
      String response = "This is the response yeey";
      //httpExchange.sendResponseHeaders(200, response.getBytes().length);


      //  POST

      if(httpExchange.getRequestMethod().equalsIgnoreCase("POST")) {
        StringBuilder body = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(httpExchange.getRequestBody(), "UTF-8")) {
          char[] buffer = new char[256];
          int read;
          while ((read = reader.read(buffer)) != -1) {
            body.append(buffer, 0, read);
          }
        }
        Headers requestHeaders = httpExchange.getRequestHeaders();
        Headers responseHeaders = httpExchange.getResponseHeaders();
        for (Map.Entry<String, List<String>> header : requestHeaders.entrySet()) {
          responseHeaders.put(header.getKey(), header.getValue());
        }
        httpExchange.getRequestBody().close();
        httpExchange.sendResponseHeaders(200, body.length() == 0 ? -1 : body.length());

        // Final Result
        if (body.length() > 0) {
          System.out.println(body.toString());
        }
        httpExchange.close();

        // Work With Data
        parseJSON(body.toString());

      }


      //  GET


      else if(httpExchange.getRequestMethod().equalsIgnoreCase("GET")) {
        System.out.println(httpExchange.getRequestBody().toString());

        System.out.println(httpExchange.getRequestBody());
//        String theString= IOUtils.toString(httpExchange.getRequestBody(),"UTF-8");

//        URI requestURI=httpExchange.getRequestURI();
//        String query=requestURI.getQuery();
//        System.out.println(query);
//
      }
    }

    public void parseJSON(String JsonString){
      JSONParser parser = new JSONParser();

      Object obj = null;
      try {
        obj = parser.parse(JsonString);
      } catch (ParseException e) {
        e.printStackTrace();
      }

      JSONObject jo=(JSONObject) obj;
      System.out.println(jo.toString());
    }
  }
}

