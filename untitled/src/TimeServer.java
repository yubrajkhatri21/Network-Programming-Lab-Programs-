import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {
    public static void main(String[] args) {
        int port = 40;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("TimeServer is running on port " + port);

            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Get the current date and time
            String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            // Send the date and time to the client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Current Date and Time: " + currentDateTime);

            // Close the connection
            clientSocket.close();
            System.out.println("Client disconnected.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
