package cron.parser;

import cron.util.ValidationUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntegerListParser implements Parser<List<Integer>> {
    @Override
    public List<Integer> parse(String expression) {
        if (!expression.contains(",")) {
            throw new IllegalArgumentException(expression + " doesn't contains ,");
        }
        String[] tokens = expression.split(",");
        return Arrays.stream(tokens)
                .map(Integer::valueOf)
                .map(ValidationUtils::checkPositive)
                .collect(Collectors.toList());
    }
}
