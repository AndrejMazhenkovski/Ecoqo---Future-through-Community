package Server;
import javax.imageio.plugins.tiff.ExifGPSTagSet;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.net.*;

public class TestClient {
    Socket socket;

    public TestClient(InetAddress ia,int port) throws IOException {
        socket=new Socket(ia,port);
    }
    public void start() throws IOException {
        PrintWriter pw=new PrintWriter(socket.getOutputStream());

        pw.println("Od klient: APPA");
        pw.flush();
    }

    public static void main(String[] args) throws IOException {
        TestClient mycl=new TestClient(InetAddress.getByName("localhost"),9876);
        mycl.start();
    }
}
