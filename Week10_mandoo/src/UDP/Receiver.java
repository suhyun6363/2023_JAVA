package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver {
       public static void main(String[] args) throws IOException {
 
             byte[] buf = new byte[256];
 
             DatagramSocket socket = new DatagramSocket(5001); 
             DatagramPacket packet = new DatagramPacket(buf, buf.length);
             socket.receive(packet);
             System.out.println(new String(buf));
       }
}
