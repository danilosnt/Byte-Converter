import service.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;
        int userId = -1;

        do {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    // Menu de login
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    userId = Login.authenticate(username, password);
                    if (userId != -1) {
                        System.out.println("Login successful!");
                        showValueMenu(scanner, userId);
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;

                case 2:
                    // Menu de registro de usuário
                    System.out.print("Enter new username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    password = scanner.nextLine();

                    if (Login.registerUser(username, password)) {
                        System.out.println("User registered successfully!");
                    } else {
                        System.out.println("Error registering user.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (option != 3);

        scanner.close();
    }

    private static void showValueMenu(Scanner scanner, int userId) {
        int valueOption;
        do {
            System.out.println("\n===== Value Menu =====");
            System.out.println("1. Register number of bytes");
            System.out.println("2. List registered numbers");
            System.out.println("3. Modify registered number");
            System.out.println("4. Delete registered number");
            System.out.println("5. Convert values");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            valueOption = scanner.nextInt();
            scanner.nextLine();

            switch (valueOption) {
                case 1:
                    registerValue(scanner, userId);
                    break;
                case 2:
                    listValues(userId);
                    break;
                case 3:
                    modifyValue(scanner, userId);
                    break;
                case 4:
                    deleteValue(scanner, userId);
                    break;
                case 5:
                    convertValues(scanner);
                    break;
                case 6:
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (valueOption != 6);
    }

    private static void registerValue(Scanner scanner, int userId) {
        System.out.print("Enter number of bytes to register: ");
        long byteValue = scanner.nextLong();
        if (ValueManager.registerValue(byteValue, userId)) {
            System.out.println("Value registered successfully.");
        } else {
            System.out.println("Error registering value.");
        }
    }

    private static void listValues(int userId) {
        ArrayList<Value> values = ValueManager.listValues(userId);
        if (values.isEmpty()) {
            System.out.println("No values found.");
        } else {
            int count = 1;
            for (Value value : values) {
                System.out.println(count + ". " + value.getByteValue() + " = " + ValueConverter.convert(value.getByteValue()));
                count++;
            }
        }
    }

    private static void modifyValue(Scanner scanner, int userId) {
        System.out.print("Enter value ID to modify: ");
        int valueId = scanner.nextInt();
        System.out.print("Enter new byte value: ");
        long newByteValue = scanner.nextLong();
        if (ValueManager.modifyValue(newByteValue, valueId, userId)) {
            System.out.println("Value modified successfully.");
        } else {
            System.out.println("Error modifying value.");
        }
    }

    private static void deleteValue(Scanner scanner, int userId) {
        System.out.print("Enter value ID to delete: ");
        int valueId = scanner.nextInt();
        if (ValueManager.deleteValue(valueId, userId)) {
            System.out.println("Value deleted successfully.");
        } else {
            System.out.println("Error deleting value.");
        }
    }

    private static void convertValues(Scanner scanner) {
        System.out.print("Enter byte value to convert: ");
        long byteValue = scanner.nextLong();
        System.out.println("Converted value: " + ValueConverter.convert(byteValue));
    }
}
