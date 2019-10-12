package Server;
import javax.imageio.plugins.tiff.ExifGPSTagSet;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.net.*;

import Internal.Person;
import org.json.simple.*;

public class TestClient {
    Socket socket;

    public TestClient(InetAddress ia,int port) throws IOException {
        socket=new Socket(ia,port);
    }
    public String makeJSON(Person testPers) throws IOException {
        JSONObject tmp=new JSONObject();
        tmp.put("name",testPers.getName());
        tmp.put("age",testPers.getAge());
        tmp.put("country","Italy");

        StringWriter o=new StringWriter();
        tmp.writeJSONString(o);
        String result=o.toString();
        return result;
    }

    public void start() throws IOException {
        Person tmpPers=new Person("Imeto","0", 12);
        String message=makeJSON(tmpPers);

        PrintWriter pw=new PrintWriter(socket.getOutputStream());

        pw.println(message);
        pw.flush();
    }

    public static void main(String[] args) throws IOException {
        TestClient mycl=new TestClient(InetAddress.getByName("localhost"),9876);
        mycl.start();
    }
}
