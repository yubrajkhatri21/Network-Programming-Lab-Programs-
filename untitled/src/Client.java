import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Lookup the Calculator object from the RMI registry
            CalculatorInterface calculator = (CalculatorInterface) Naming.lookup("rmi://localhost:1200/Calculator");

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\nSimple Calculator");
                System.out.println("1. Add");
                System.out.println("2. Subtract");
                System.out.println("3. Multiply");
                System.out.println("4. Divide");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                if (choice == 5) {
                    break;
                }

                System.out.print("Enter first number: ");
                int num1 = scanner.nextInt();
                System.out.print("Enter second number: ");
                int num2 = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Result: " + calculator.add(num1, num2));
                        break;
                    case 2:
                        System.out.println("Result: " + calculator.subtract(num1, num2));
                        break;
                    case 3:
                        System.out.println("Result: " + calculator.multiply(num1, num2));
                        break;
                    case 4:
                        try {
                            System.out.println("Result: " + calculator.divide(num1, num2));
                        } catch (RemoteException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
