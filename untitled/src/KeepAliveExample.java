// Unit 4: KeepAliveExample.java
import java.io.*;
import java.net.*;

public class KeepAliveExample {
    public static void main(String[] args) {
        try {
            // Specify the target URL
            URL url = new URL("http://www.example.com");

            // Open HTTP connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method and headers
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Connection", "keep-alive");

            // Connect to the server
            connection.connect();

            // Display response status
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Message: " + connection.getResponseMessage());

            // Read and display response content
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            String line;
            System.out.println("\nResponse Content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
            connection.disconnect(); // You can omit this to let connection persist (if supported)

        } catch (MalformedURLException e) {
            System.out.println("Invalid URL format.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O Error occurred.");
            e.printStackTrace();
        }
    }
}
