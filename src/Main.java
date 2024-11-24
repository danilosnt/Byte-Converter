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
            System.out.println("3. Convert values");
            System.out.println("4. Exit");
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
                    convertValues();
                    break;
                case 4:
                    System.out.println("Ending the program...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } while (option != 4);

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
