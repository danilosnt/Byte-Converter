package service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ValueConverter {
    public static String convert(long byteValue) {
        String[] units = {"bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
        BigDecimal value = new BigDecimal(byteValue);
        BigDecimal oneKB = new BigDecimal(1024);
        int unitIndex = 0;

        while (value.compareTo(oneKB) >= 0 && unitIndex < units.length - 1) {
            value = value.divide(oneKB, 2, RoundingMode.HALF_UP);
            unitIndex++;
        }

        return value.toString().replace(".", ",") + " " + units[unitIndex];
    }
}
