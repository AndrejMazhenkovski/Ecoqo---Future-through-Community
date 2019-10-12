package Server;
import Internal.Organization;
import Internal.Person;

import javax.imageio.plugins.tiff.ExifGPSTagSet;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.net.*;

import Internal.Sponsor;
import Internal.User;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TCPServer {

    public ServerSocket serverSocket;
    public int ServerPort;

    public HashMap<Integer,Person> hashPersons;     // store persons
    public HashMap<Integer, Organization> hashOrg;  // store organzations
    public TreeSet<Sponsor> treeSponsors;


    public TCPServer() {
        try {
            this.serverSocket = new ServerSocket(9876);
            ServerPort=9876;
        }
        catch (IOException exc){
            System.out.println("Sth wrong");
        }
        initializeLocalRepos();
    }

    public void initializeLocalRepos(){
        hashPersons = new HashMap<Integer, Person>();  //Comparator.comparing(Person::getAge)
        hashOrg = new HashMap<Integer,Organization>();
        treeSponsors = new TreeSet<>();
    }

    public void listen(){
        String clientJSON;

        try {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("New Connection from: " + client.getInetAddress());

                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println(clientJSON=br.readLine());

                // Main things
                identifyMessage();

                if (identifyMessage()==1) {    // Registration
                    User testUsr = new Person("Jo","20",4);
                    User testUsr2 = new Person("Males","12",3);
                    this.putInRepo(testUsr,1);
                    this.putInRepo(testUsr2,1);
                    //this.parseJSONMessage(clientJSON);
                }

                //Person testPers=new Person(klient,"0");
                //this.putInHashMap(testPers);
                //System.out.println(hashPersons.get(testPers.hashCode()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int identifyMessage(){
        // returns type of message
        return 1;
    }

    public void putInRepo(User testUser, int TYPE){   //TYPE=1 Person, TYPE=2 Organization, TYPE=3 Sponsor
        if(TYPE==1) {
            hashPersons.computeIfAbsent(testUser.hashCode(), val -> (Person)testUser);
            hashPersons.computeIfPresent(testUser.hashCode(), (k, v) -> {
              // tuka na pr da szgolemuva broj na pojavuvanja
              return v;
            });
        }
        else if(TYPE==2){

        }
        else if(TYPE==3){

        }
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
