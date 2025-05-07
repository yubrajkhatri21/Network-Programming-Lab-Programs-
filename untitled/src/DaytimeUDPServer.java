import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DaytimeUDPServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Create a DatagramSocket on port 13
            socket = new DatagramSocket(13);
            System.out.println("Daytime UDP Server is running on port 13...");

            byte[] receiveData = new byte[1024];
            while (true) {
                // Receive request from client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                // Get client's address and port from the received packet
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Get current date and time
                String currentDateTime = getCurrentDateTime();

                // Convert the date and time to bytes
                byte[] sendData = currentDateTime.getBytes();

                // Send the date and time to the client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    // Method to get the current date and time in a specific format
    private static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
