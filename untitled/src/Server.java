import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) {
        try {
            // Start the RMI registry on port 1200
            LocateRegistry.createRegistry(1200);
            System.out.println("RMI registry started on port 1200.");

            // Create an instance of the CalculatorImpl
            CalculatorImpl calculator = new CalculatorImpl();

            // Bind the calculator object to the RMI registry
            Naming.rebind("rmi://localhost:1200/Calculator", calculator);
            System.out.println("Calculator server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
