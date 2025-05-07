// Unit 6: LowPortScanner.java
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;

public class LowPortScanner {
    public static void main(String[] args) {
        String host = "localhost"; // You can change this to scan another host

        System.out.println("Scanning ports 1 to 80 on host: " + host + "\n");

        for (int port = 1; port <= 80; port++) {
            try {
                // Try to create a socket connection to the host and port
                Socket socket = new Socket(InetAddress.getByName(host), port);
                System.out.println("Port " + port + " is OPEN.");
                socket.close();
            } catch (IOException e) {
                // Port is closed or unreachable
                // System.out.println("Port " + port + " is CLOSED.");
            }
        }

        System.out.println("\nScan complete.");
    }
}
