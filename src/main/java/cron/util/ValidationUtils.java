package cron.util;

import java.util.List;

public class ValidationUtils {

    public static List<Integer> checkValues(List<Integer> values, int minimum, int maximum) {
        boolean valid = values.stream().allMatch(v -> v >= minimum && v <= maximum && v >= 0);
        if (!valid) {
            throw new IllegalArgumentException("Values are not in (" + minimum + "-" + maximum + ")");
        }
        return values;
    }

    public static int checkPositive(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value is negative");
        }
        return value;
    }
}
