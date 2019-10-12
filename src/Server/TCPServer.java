package Server;
import Internal.Organization;
import Internal.Person;

import javax.imageio.plugins.tiff.ExifGPSTagSet;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TCPServer {

    public ServerSocket serverSocket;
    public int ServerPort;
    public HashMap<Integer,Person> hashPersons;     // store persons
    public HashMap<Integer, Organization> hashOrg;

    public TCPServer() {
        try {
            this.serverSocket = new ServerSocket(9876);
            ServerPort=9876;
        }
        catch (IOException exc){
            System.out.println("Sth wrong");
        }

        hashPersons = new HashMap<Integer, Person>();  //Comparator.comparing(Person::getAge)
    }
    public void listen(){
        String klient;

        try {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("New Connection from: " + client.getInetAddress());

                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println(klient=br.readLine());

                this.parseJSONMessage(klient);

                //Person testPers=new Person(klient,"0");
                //this.putInHashMap(testPers);
                //System.out.println(hashPersons.get(testPers.hashCode()));
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void putInHashMap(Person testPers){
        hashPersons.computeIfAbsent(testPers.hashCode(), val-> testPers);
        hashPersons.computeIfPresent(testPers.hashCode(), (k,v)->{
            // tuka na pr da szgolemuva broj na pojavuvanja
            return v;
        });
    }

    public HashMap<String,String> parseJSONMessage(String jsonStr) throws ParseException {
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(jsonStr);
        JSONObject jo=(JSONObject) obj;

        // do tuka e okej
        HashMap <String,String> internalHash =new HashMap<>();
        jo.keySet().stream().forEach(k -> internalHash.put(k.toString(),jo.get(k).toString()));

        return internalHash;
    }

    public static void main(String[] args) {
        TCPServer myserv=new TCPServer();
        myserv.listen();
    }


}
