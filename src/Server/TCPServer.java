package Server;
import Internal.Person;

import javax.imageio.plugins.tiff.ExifGPSTagSet;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.net.*;

public class TCPServer {

    public ServerSocket serverSocket;
    public int ServerPort;
    public HashMap<Integer,Person> hashMap;

    public TCPServer() {
        try {
            this.serverSocket = new ServerSocket(9876);
            ServerPort=9876;
        }
        catch (IOException exc){
            System.out.println("Sth wrong");
        }

        hashMap = new HashMap<Integer, Person>();  //Comparator.comparing(Person::getAge)
    }
    public void listen(){
        String klient;

        try {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("New Connection from: " + client.getInetAddress());

                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println(klient=br.readLine());

                Person testPers=new Person(klient,"0");
                this.putInHashMap(testPers);

                System.out.println(hashMap.get(testPers.hashCode()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void putInHashMap(Person testPers){
        hashMap.computeIfAbsent(testPers.hashCode(), val-> testPers);
        hashMap.computeIfPresent(testPers.hashCode(), (k,v)->{
            // tuka na pr da szgolemuva broj na pojavuvanja
            return v;
        });
    }

    public static void main(String[] args) {
        TCPServer myserv=new TCPServer();
        myserv.listen();
    }


}
