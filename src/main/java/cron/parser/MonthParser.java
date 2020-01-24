package cron.parser;

import cron.model.Interval;
import cron.model.Month;

import java.util.Arrays;
import java.util.List;

public class MonthParser implements Parser<Month> {

    private final IntegerListParser integerListParser;
    private final IntervalParser intervalParser;

    public MonthParser(IntegerListParser integerListParser, IntervalParser intervalParser) {
        this.integerListParser = integerListParser;
        this.intervalParser = intervalParser;
    }

    @Override
    public Month parse(String expression) {
        if (expression.length() == 1) {
            char firstChar = expression.charAt(0);
            if (firstChar == '*') {
                return Month.ALL_POSSIBLE_VALUES();
            }
        } else if (expression.contains(",")) {
            List<Integer> values = integerListParser.parse(expression);
            return new Month(values);
        } else if (expression.contains("-")) {
            Interval interval = intervalParser.parse(expression);
            return Month.createFromInterval(interval);
        }
        return new Month(Arrays.asList(Integer.valueOf(expression)));
    }
}
