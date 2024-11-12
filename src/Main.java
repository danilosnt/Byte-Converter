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
        BigInteger oneMB = oneKB.multiply(oneKB);
        BigInteger oneGB = oneMB.multiply(oneKB);
        BigInteger oneTB = oneGB.multiply(oneKB);
        BigInteger onePB = oneTB.multiply(oneKB);
        BigInteger oneEB = onePB.multiply(oneKB);
        BigInteger oneZB = oneEB.multiply(oneKB);
        BigInteger oneYB = oneZB.multiply(oneKB);

        if (bytes.compareTo(oneYB) >= 0 ) {
            BigDecimal yb = new BigDecimal(bytes).divide(new BigDecimal(oneYB), 2, RoundingMode.HALF_UP);
            return yb.toString().replace(".", ",") + " YB";
        }

        else if (bytes.compareTo(oneZB) >= 0 ) {
            BigDecimal zb = new BigDecimal(bytes).divide(new BigDecimal(oneZB),2, RoundingMode.HALF_UP);
            return zb.toString().replace(".", ",") + " ZB";
        }

        else if (bytes.compareTo(oneEB) >= 0 ) {
            BigDecimal eb = new BigDecimal(bytes).divide(new BigDecimal(oneEB), 2, RoundingMode.HALF_UP);
            return eb.toString().replace(".", ",") + " EB";
        }

        else if (bytes.compareTo(onePB) >= 0 ) {
            BigDecimal pb = new BigDecimal(bytes).divide(new BigDecimal(onePB), 2, RoundingMode.HALF_UP);
            return pb.toString().replace(".", ",") + " PB";
        }

        else if (bytes.compareTo(oneTB) >= 0) {
            BigDecimal tb = new BigDecimal(bytes).divide(new BigDecimal(oneTB), 2, RoundingMode.HALF_UP);
            return tb.toString().replace(".", ",") + " TB";
        }

        else if (bytes.compareTo(oneGB) >= 0) {
            BigDecimal gb = new BigDecimal(bytes).divide(new BigDecimal(oneGB), 2, RoundingMode.HALF_UP);
            return gb.toString().replace(".", ",") + " GB";
        }

        else if (bytes.compareTo(oneMB) >= 0) {
            BigDecimal mb = new BigDecimal(bytes).divide(new BigDecimal(oneMB), 2, RoundingMode.HALF_UP);
            return mb.toString().replace(".", ",") + " MB";
        }

        else if (bytes.compareTo(oneKB) >= 0) {
            BigDecimal kb = new BigDecimal(bytes).divide(new BigDecimal(oneKB), 2, RoundingMode.HALF_UP);
            return kb.toString().replace(".", ",") + " KB";
        }

        else {
            return bytes.toString() + " bytes";
        }
    }
}