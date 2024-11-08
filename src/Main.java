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
        BigInteger oneKB = new BigInteger("1024");
        BigInteger oneMB = oneKB.multiply(oneKB); // 1024 KB
        BigInteger oneGB = oneMB.multiply(oneKB); // 1024 MB
        BigInteger oneTB = oneGB.multiply(oneKB); // 1024 GB

        // If bytes >= 1 TB
        if (bytes.compareTo(oneTB) >= 0) {
            BigDecimal tb = new BigDecimal(bytes).divide(new BigDecimal(oneTB), 2, RoundingMode.HALF_UP);
            return tb.toString().replace(".", ",") + " TB";
        }
        // If bytes >= 1 GB
        else if (bytes.compareTo(oneGB) >= 0) {
            BigDecimal gb = new BigDecimal(bytes).divide(new BigDecimal(oneGB), 2, RoundingMode.HALF_UP);
            return gb.toString().replace(".", ",") + " GB";
        }
        // If bytes >= 1 MB
        else if (bytes.compareTo(oneMB) >= 0) {
            BigDecimal mb = new BigDecimal(bytes).divide(new BigDecimal(oneMB), 2, RoundingMode.HALF_UP);
            return mb.toString().replace(".", ",") + " MB";
        }
        // If bytes >= 1 KB
        else if (bytes.compareTo(oneKB) >= 0) {
            BigDecimal kb = new BigDecimal(bytes).divide(new BigDecimal(oneKB), 2, RoundingMode.HALF_UP);
            return kb.toString().replace(".", ",") + " KB";
        }
        // Return bytes if (bytes > 1kb)
        else {
            return bytes.toString() + " bytes";
        }
    }
}