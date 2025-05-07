import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DictClient {
    public static void main(String[] args) {
        String server = "dict.org";
        int port = 2628;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an English word to get its meaning: ");
        String word = scanner.nextLine();

        try (
                Socket socket = new Socket(server, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            // Welcome
            in.readLine();

            // Request a list of databases
            out.println("SHOW DB");
            System.out.println("Available Databases:");
            String dbLine;
            while (!(dbLine = in.readLine()).startsWith("250")) {
                System.out.println(dbLine);
            }

            // Choose "english" (for general English dictionary lookup)
            out.println("DEFINE english " + word);

            String line;
            while (!(line = in.readLine()).startsWith("250")) {
                if (line.startsWith("552")) {
                    System.out.println("No definition found for: " + word);
                    break;
                }
                System.out.println(line);
            }

            out.println("QUIT");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
