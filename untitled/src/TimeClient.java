import java.io.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 40;

        try (Socket socket = new Socket(serverAddress, port)) {
            // Read the date and time sent by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverDateTime = in.readLine();

            System.out.println("Date and Time from Server: " + serverDateTime);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
