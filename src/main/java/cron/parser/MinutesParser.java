package cron.parser;

import cron.model.Interval;
import cron.model.Minutes;

import java.util.Arrays;
import java.util.List;

public class MinutesParser implements Parser<Minutes> {

    private final IntegerListParser integerListParser;
    private final IntervalParser intervalParser;

    public MinutesParser(IntegerListParser integerListParser, IntervalParser intervalParser) {
        this.integerListParser = integerListParser;
        this.intervalParser = intervalParser;
    }

    @Override
    public Minutes parse(String expression) {
        if (expression.length() == 1) {
            char firstChar = expression.charAt(0);
            if (firstChar == '*') {
                return Minutes.ALL_POSSIBLE_VALUES();
            }
        } else if (expression.contains(",")) {
            List<Integer> values = integerListParser.parse(expression);
            return new Minutes(values);
        } else if (expression.contains("-")) {
            Interval interval = intervalParser.parse(expression);
            return Minutes.createFromInterval(interval);
        }
        return new Minutes(Arrays.asList(Integer.valueOf(expression)));
    }
}
