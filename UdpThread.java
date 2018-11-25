package charis.com.smartremotecontrol;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Charis on 04-Jun-16.


 UDP thread

 */
public class UdpThread implements Runnable {

    String command;


    public UdpThread(String button){

        command=button;

    }

    @Override
    public void run() {
        try {
            String host = "192.168.10.100";
            int port = 8888;
System.out.println("Command="+command);
            byte[] message =command.getBytes();
                   /// "NEC/2160034684/32".getBytes();

            // Get the internet address of the specified host
            InetAddress address = InetAddress.getByName(host);

            // Initialize a datagram packet with data and address
            DatagramPacket packet = new DatagramPacket(message, message.length,
                    address, port);

            // Create a datagram socket, send the packet through it, close it.
            DatagramSocket dsocket = new DatagramSocket();
            dsocket.send(packet);
            dsocket.close();
        } catch (Exception e) {
            System.err.println("Error on thread");
            e.printStackTrace();
        }
    }
}
