import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<BigInteger> values = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Register number of bytes");
            System.out.println("2. List registered numbers");
            System.out.println("3. Modify registered number");
            System.out.println("4. Delete registered number");
            System.out.println("5. Convert values");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    registerBytes(scanner);
                    break;
                case 2:
                    listValues();
                    break;
                case 3:
                    modifyValue(scanner);
                    break;
                case 4:
                    deleteValue(scanner);
                    break;
                case 5:
                    convertValues();
                    break;
                case 6:
                    System.out.println("Ending the program...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } while (option != 6);

        scanner.close();
    }

    private static void registerBytes(Scanner scanner) {
        System.out.print("Enter the number of bytes: ");
        BigInteger value = scanner.nextBigInteger();

        if (value.equals(BigInteger.ZERO)) {
            System.out.println("0 bytes cannot be registered. Returning to the menu...");
            return;
        }

        values.add(value);
        System.out.println("\nValue registered successfully!");
    }

    private static void listValues() {
        if (values.isEmpty()) {
            System.out.println("\nNo value registered.");
        } else {
            System.out.println("\nRegistered values:");
            for (int i = 0; i < values.size(); i++) {
                System.out.printf("%d. %s bytes%n", i + 1, values.get(i).toString());
            }
        }
    }

    private static void modifyValue(Scanner scanner) {
        if (values.isEmpty()) {
            System.out.println("\nNo value to modify.");
            return;
        }

        listValues();
        System.out.print("\nEnter the number of the value you want to modify: ");
        int index = scanner.nextInt();

        if (index < 1 || index > values.size()) {
            System.out.println("Invalid index. Returning to the menu...");
            return;
        }

        System.out.print("Enter the new number of bytes: ");
        BigInteger newValue = scanner.nextBigInteger();

        if (newValue.equals(BigInteger.ZERO)) {
            System.out.println("0 bytes cannot be registered. Returning to the menu...");
            return;
        }

        values.set(index - 1, newValue);
        System.out.println("\nValue modified successfully!");
    }

    private static void deleteValue(Scanner scanner) {
        if (values.isEmpty()) {
            System.out.println("\nNo value to delete.");
            return;
        }

        listValues();
        System.out.print("\nEnter the number of the value you want to delete: ");
        int index = scanner.nextInt();

        if (index < 1 || index > values.size()) {
            System.out.println("Invalid index. Returning to the menu...");
            return;
        }

        values.remove(index - 1);
        System.out.println("\nValue deleted successfully!");
    }

    private static void convertValues() {
        if (values.isEmpty()) {
            System.out.println("\nNo value to convert.");
        } else {
            System.out.println("\nConversion results:");
            for (BigInteger value : values) {
                System.out.printf("%s bytes = %s%n", value.toString(), formatBytes(value));
            }
        }
    }

    private static String formatBytes(BigInteger bytes) {
        String[] units = {"bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
        BigInteger oneKB = new BigInteger("1024");

        int unitIndex = 0;
        BigDecimal value = new BigDecimal(bytes);

        while (value.compareTo(new BigDecimal(oneKB)) >= 0 && unitIndex < units.length - 1) {
            value = value.divide(new BigDecimal(oneKB), 2, RoundingMode.HALF_UP);
            unitIndex++;
        }

        return value.toString().replace(".", ",") + " " + units[unitIndex];
    }
}
