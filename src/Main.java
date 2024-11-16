import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    static BigInteger[] quantityNumber;
    static BigInteger bytes;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the numbers of values to be converterd: ");
        int quantity = scanner.nextInt();

        quantityNumber = new BigInteger[quantity];

        // Suggested number: 1208925819614629174706175 (1 YB)
        for (int i = 0; i < quantity; i++) {
            System.out.print("Enter the numbers of bytes: ");
            bytes = scanner.nextBigInteger();
            quantityNumber[i] = bytes;
        }

        for (BigInteger byteValue : quantityNumber) {
            String result = formatBytes(byteValue);
            System.out.printf("%s bytes = %s%n", byteValue.toString(), result);
        }
        scanner.close();
    }

    public static String formatBytes(BigInteger bytes) {
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