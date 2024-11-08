import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    static BigInteger[] vetor;
    static BigInteger bytes;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a quantidade de números: ");
        int qtd = scanner.nextInt();

        // Inicializando o array com o tamanho especificado
        vetor = new BigInteger[qtd];

        for (int i = 0; i < qtd; i++) {
            System.out.print("Digite o número de bytes: ");
            bytes = scanner.nextBigInteger();
            vetor[i] = bytes;
        }

        // Convertendo e exibindo o resultado para cada valor em `vetor`
        for (BigInteger byteValue : vetor) {
            String result = formatBytes(byteValue);
            System.out.printf("%s bytes = %s%n", byteValue.toString(), result);
        }

        scanner.close();
    }


    // Método para formatar o valor em KB, MB, GB ou TB com duas casas decimais e vírgula
    public static String formatBytes(BigInteger bytes) {
        BigInteger oneKB = new BigInteger("1024");
        BigInteger oneMB = oneKB.multiply(oneKB); // 1024 KB
        BigInteger oneGB = oneMB.multiply(oneKB); // 1024 MB
        BigInteger oneTB = oneGB.multiply(oneKB); // 1024 GB

        // Caso bytes >= 1 TB
        if (bytes.compareTo(oneTB) >= 0) {
            BigDecimal tb = new BigDecimal(bytes).divide(new BigDecimal(oneTB), 2, RoundingMode.HALF_UP);
            return tb.toString().replace(".", ",") + " TB";
        }
        // Caso bytes >= 1 GB
        else if (bytes.compareTo(oneGB) >= 0) {
            BigDecimal gb = new BigDecimal(bytes).divide(new BigDecimal(oneGB), 2, RoundingMode.HALF_UP);
            return gb.toString().replace(".", ",") + " GB";
        }
        // Caso bytes >= 1 MB
        else if (bytes.compareTo(oneMB) >= 0) {
            BigDecimal mb = new BigDecimal(bytes).divide(new BigDecimal(oneMB), 2, RoundingMode.HALF_UP);
            return mb.toString().replace(".", ",") + " MB";
        }
        // Caso bytes >= 1 KB
        else if (bytes.compareTo(oneKB) >= 0) {
            BigDecimal kb = new BigDecimal(bytes).divide(new BigDecimal(oneKB), 2, RoundingMode.HALF_UP);
            return kb.toString().replace(".", ",") + " KB";
        }
        // Retornar bytes em caso de ser menor que 1 KB
        else {
            return bytes.toString() + " bytes";
        }
    }
}