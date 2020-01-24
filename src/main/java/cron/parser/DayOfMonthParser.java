package cron.parser;

import cron.model.DayOfMonth;
import cron.model.Interval;

import java.util.Arrays;
import java.util.List;

public class DayOfMonthParser implements Parser<DayOfMonth> {

    private final IntegerListParser integerListParser;
    private final IntervalParser intervalParser;

    public DayOfMonthParser(IntegerListParser integerListParser, IntervalParser intervalParser) {
        this.integerListParser = integerListParser;
        this.intervalParser = intervalParser;
    }

    @Override
    public DayOfMonth parse(String expression) {
        if (expression.length() == 1) {
            char firstChar = expression.charAt(0);
            if (firstChar == '*') {
                return DayOfMonth.ALL_POSSIBLE_VALUES();
            }
        } else if (expression.contains(",")) {
            List<Integer> values = integerListParser.parse(expression);
            return new DayOfMonth(values);
        } else if (expression.contains("-")) {
            Interval interval = intervalParser.parse(expression);
            return DayOfMonth.createFromInterval(interval);
        }
        return new DayOfMonth(Arrays.asList(Integer.valueOf(expression)));
    }
}
