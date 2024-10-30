import java.math.BigInteger;

public class ByteToMbConverter {
    public static void main(String[] args) {
        // Insira os Bytes abaixo.
        BigInteger[] byteValues = {
                new BigInteger("10240000000000"), // Exemplo de valor
        };

        // Convertendo bytes para KB, MB, GB ou TB
        for (BigInteger bytes : byteValues) {
            String result = formatBytes(bytes);
            System.out.printf("%s bytes = %s%n", bytes.toString(), result);
        }
    }

    // Método para formatar o valor em KB, MB, GB ou TB
    public static String formatBytes(BigInteger bytes) {
        BigInteger oneKB = new BigInteger("1024"); // 1024 bytes
        BigInteger oneMB = oneKB.multiply(new BigInteger("1024")); // 1024 KB
        BigInteger oneGB = oneMB.multiply(new BigInteger("1024")); // 1024 MB
        BigInteger oneTB = oneGB.multiply(new BigInteger("1024")); // 1024 GB

        // Caso bytes >= 1 TB
        if (bytes.compareTo(oneTB) >= 0) {
            BigInteger tb = bytes.divide(oneTB);
            return String.format("%.2f TB", tb.doubleValue() + (bytes.remainder(oneTB).doubleValue() / oneTB.doubleValue()));
        }
        // Caso bytes >= 1 GB
        else if (bytes.compareTo(oneGB) >= 0) {
            BigInteger gb = bytes.divide(oneGB);
            return String.format("%.2f GB", gb.doubleValue() + (bytes.remainder(oneGB).doubleValue() / oneGB.doubleValue()));
        }
        // Caso bytes >= 1 MB
        else if (bytes.compareTo(oneMB) >= 0) {
            BigInteger mb = bytes.divide(oneMB);
            return String.format("%.2f MB", mb.doubleValue() + (bytes.remainder(oneMB).doubleValue() / oneMB.doubleValue()));
        }
        // Caso bytes >= 1 KB
        else if (bytes.compareTo(oneKB) >= 0) {
            BigInteger kb = bytes.divide(oneKB);
            return String.format("%.2f KB", kb.doubleValue() + (bytes.remainder(oneKB).doubleValue() / oneKB.doubleValue()));
        }
        // Retornar bytes em caso de ser menor que 1 KB
        else {
            return String.format("%s bytes", bytes.toString());
        }
    }
}
