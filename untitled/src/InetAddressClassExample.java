// Unit 2: InetAddressClassExample.java
import java.net.*;

public class InetAddressClassExample {
    public static void main(String[] args) {
        try {
            // Provide the URL here
            String host = "www.google.com";

            // Get all IP addresses associated with the host
            InetAddress[] addresses = InetAddress.getAllByName(host);

            System.out.println("Canonical Host Names for: " + host);
            for (InetAddress address : addresses) {
                System.out.println("IP Address: " + address.getHostAddress());
                System.out.println("Canonical Host Name: " + address.getCanonicalHostName());
                System.out.println("--------------------------------------");
            }
        } catch (UnknownHostException e) {
            System.out.println("Host could not be resolved.");
            e.printStackTrace();
        }
    }
}
