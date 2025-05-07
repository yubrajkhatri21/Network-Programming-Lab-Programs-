import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.cert.Certificate;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;

public class HTTPSClient {
    public static void main(String[] args) {
        String host = "www.example.com"; // Target website
        int port = 443; // Default HTTPS port

        try {
            // Create SSL context and initialize the SSL context with default trust manager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new TrustAllCertificates()}, new java.security.SecureRandom());

            // Create SSLSocketFactory from the SSL context
            SSLSocketFactory factory = sslContext.getSocketFactory();

            // Create an SSLSocket from the factory
            SSLSocket sslSocket = (SSLSocket) factory.createSocket(host, port);

            // Enable all available SSL protocols
            sslSocket.setEnabledProtocols(sslSocket.getSupportedProtocols());

            // Start the handshake with the server
            sslSocket.startHandshake();

            // Print certificate details (optional)
            printCertificateDetails(sslSocket);

            // Get the output stream to send the HTTP GET request
            PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);

            // Send an HTTP GET request
            out.println("GET / HTTP/1.1");
            out.println("Host: " + host);
            out.println("Connection: close");
            out.println();  // End of headers

            // Get the input stream to read the server response
            BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            String line;

            // Read and print the response (HTML content)
            System.out.println("Response from server:");
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            // Close the SSL socket
            in.close();
            out.close();
            sslSocket.close();

        } catch (NoSuchAlgorithmException | KeyManagementException | IOException e) {
            e.printStackTrace();
        }
    }

    // Trust manager that accepts all certificates (Note: This is insecure for production use)
    private static class TrustAllCertificates implements X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }
    }

    // Method to print certificate details
    private static void printCertificateDetails(SSLSocket sslSocket) {
        try {
            SSLSession session = sslSocket.getSession();
            Certificate[] certs = session.getPeerCertificates();
            for (Certificate cert : certs) {
                System.out.println("Certificate: " + cert);
            }
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        }
    }
}
