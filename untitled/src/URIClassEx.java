// Unit 3: URIClassEx.java
import java.net.*;

public class URIClassEx {
    public static void main(String[] args) {
        try {
            // Create a URI instance with a sample URI
            URI uri = new URI("https://www.example.com:8080/path/to/resource?query=hello#section1");

            // Displaying different components of the URI
            System.out.println("URI: " + uri.toString());
            System.out.println("Scheme: " + uri.getScheme());
            System.out.println("Authority: " + uri.getAuthority());
            System.out.println("Host: " + uri.getHost());
            System.out.println("Port: " + uri.getPort());
            System.out.println("Path: " + uri.getPath());
            System.out.println("Query: " + uri.getQuery());
            System.out.println("Fragment: " + uri.getFragment());
            System.out.println("User Info: " + uri.getUserInfo());
        } catch (URISyntaxException e) {
            System.out.println("Invalid URI syntax.");
            e.printStackTrace();
        }
    }
}
