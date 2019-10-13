package Server;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.CallableStatement;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

import Internal.Organization;
import Internal.Person;
import Internal.Sponsor;
import Internal.User;
import org.json.simple.*;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HTTPServerWorks {

  public static HashMap<Integer, Person> hashPersons;     // store persons
  public static HashMap<Integer, Organization> hashOrg;  // store organzations
  public static TreeSet<Sponsor> treeSponsors;

  public static void initializeLocalRepos(){
    hashPersons = new HashMap<Integer, Person>();  //Comparator.comparing(Person::getAge)
    hashOrg = new HashMap<Integer,Organization>();
    treeSponsors = new TreeSet<>();
  }

  public static void main(String[] args) throws Exception {

    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);  // creates server
    initializeLocalRepos();
    server.createContext("/", new MyHandler());   // adds a basic handler (Needed implementation)
    server.setExecutor(null);    // creates a default executor
    server.start();
    System.out.println("__Server active__");

  }

  static class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

      // not needed
//      String response = "This is the response yeey";
//      httpExchange.sendResponseHeaders(200, response.getBytes().length);

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
        processData(parseJSON(body.toString()));

      }


      // Useless piece of poopehyos down there

      //  GET


      else if(httpExchange.getRequestMethod().equalsIgnoreCase("GET")) {
         System.out.println(httpExchange.getRequestBody().toString());
         System.out.println(httpExchange.getRequestBody());

//        URL url = new URL("http://localhost:8000");
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//
//        con.setRequestProperty("Content-Type", "application/json");   // add header
//        String contentType = con.getHeaderField("Content-Type");


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create("http://webcode.me"))
          .build();

        HttpResponse<String> response = null;
        try {
          response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        System.out.println(response.body());

      }
    }

    public JSONObject parseJSON(String JsonString){
      JSONParser parser = new JSONParser();


      Object obj = null;
      try {
        obj = parser.parse(JsonString);
      } catch (ParseException e) {
        e.printStackTrace();
      }

      JSONObject jo=(JSONObject) obj;

      return jo;
      //System.out.println(jo.get("lastName"));
      //System.out.println(jo.toString());
    }

    // nedovrsena
    public void processData(JSONObject jsonObj){

      if(jsonObj.get("type").toString().equals("Register")){
        if(jsonObj.get("user").toString().equals("1")){       // a volunteer registers
          Person tmpPers = new Person(jsonObj.get("firstName").toString(),jsonObj.get("age").toString(),10);
          // storing in local memory
          putInRepo(tmpPers,1);
        }
      }
      else if(jsonObj.get("type").toString().equals("Update total")){

      }
    }

    // nedovrsena
    public void putInRepo(User testUser, int TYPE){   //TYPE=1 Person, TYPE=2 Organization, TYPE=3 Sponsor
      if(TYPE==1) {
        hashPersons.computeIfAbsent(testUser.hashCode(), val -> (Person)testUser);
        hashPersons.computeIfPresent(testUser.hashCode(), (k, v) -> {
          // tuka na pr da szgolemuva broj na pojavuvanja
          return v;
        });
      }
      else if(TYPE==2){
        hashOrg.computeIfAbsent(testUser.hashCode(), val -> (Organization)testUser);
        hashOrg.computeIfPresent(testUser.hashCode(),(k,v)->{
          // se zgolemuva br na pojavuvanja
          return v;
        });
      }
      else if(TYPE==3){

      }
    }
  }
}

