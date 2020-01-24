package cron.parser;

import cron.model.Hours;
import cron.model.Interval;

import java.util.Arrays;
import java.util.List;

public class HoursParser implements Parser<Hours> {

    private final IntegerListParser integerListParser;
    private final IntervalParser intervalParser;

    public HoursParser(IntegerListParser integerListParser, IntervalParser intervalParser) {
        this.integerListParser = integerListParser;
        this.intervalParser = intervalParser;
    }

    @Override
    public Hours parse(String expression) {
        if (expression.length() == 1) {
            char firstChar = expression.charAt(0);
            if (firstChar == '*') {
                return Hours.ALL_POSSIBLE_VALUES();
            }
        } else if (expression.contains(",")) {
            List<Integer> values = integerListParser.parse(expression);
            return new Hours(values);
        } else if (expression.contains("-")) {
            Interval interval = intervalParser.parse(expression);
            return Hours.createFromInterval(interval);
        }
        return new Hours(Arrays.asList(Integer.valueOf(expression)));
    }
}
