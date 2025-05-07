// Unit 5: URLConnectionExample.java
import java.net.*;
import java.io.*;
import java.util.Date;

public class URLConnectionExample {
    public static void main(String[] args) {
        try {
            // Specify the target URL
            URL url = new URL("http://www.example.com");

            // Open a connection to the URL
            URLConnection connection = url.openConnection();

            // Connect to the URL (optional as getXXX methods will implicitly do this)
            connection.connect();

            // Display content information using multiple methods
            System.out.println("------ Content Information ------");

            System.out.println("Content Type: " + connection.getContentType());
            System.out.println("Content Length: " + connection.getContentLength());
            System.out.println("Content Encoding: " + connection.getContentEncoding());
            System.out.println("Date: " + new Date(connection.getDate()));
            System.out.println("Expiration: " + new Date(connection.getExpiration()));
            System.out.println("Last Modified: " + new Date(connection.getLastModified()));

            System.out.println("----------------------------------");

            // Optional: Display content (first few lines)
            System.out.println("\n------ Page Content (Preview) ------");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null && lineCount < 10) {
                System.out.println(line);
                lineCount++;
            }

            reader.close();

        } catch (MalformedURLException e) {
            System.out.println("Invalid URL.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading from the URL.");
            e.printStackTrace();
        }
    }
}
