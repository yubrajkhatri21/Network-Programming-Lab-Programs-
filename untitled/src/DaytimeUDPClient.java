import java.net.*;

public class DaytimeUDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Create a DatagramSocket
            socket = new DatagramSocket();

            // Server details (host and port)
            String serverAddress = "localhost";  // Server running on the same machine
            int serverPort = 13;  // Server port

            // Create a packet to send to the server
            byte[] sendData = new byte[1];  // Just a small dummy packet
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(serverAddress), serverPort);

            // Send the request to the server
            socket.send(sendPacket);
            System.out.println("Sent request to the server for date and time...");

            // Prepare to receive the server response
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Receive the server response (date and time)
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Print the received date and time
            System.out.println("Received from server: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
