// Unit 3: MyURLClass.java
import java.io.*;
import java.net.*;

public class MyURLClass {
    public static void main(String[] args) {
        try {
            // Create a URL object
            URL url = new URL("http://www.example.com");

            // Open a connection to the URL and get an InputStream
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );

            String line;
            System.out.println("Source code of www.example.com:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
        } catch (MalformedURLException e) {
            System.out.println("The URL is malformed.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An I/O error occurred while reading from the URL.");
            e.printStackTrace();
        }
    }
}
