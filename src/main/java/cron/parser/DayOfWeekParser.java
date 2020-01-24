package cron.parser;

import cron.model.DayOfWeek;
import cron.model.Hours;
import cron.model.Interval;

import java.util.Arrays;
import java.util.List;

public class DayOfWeekParser implements Parser<DayOfWeek> {

    private final IntegerListParser integerListParser;
    private final IntervalParser intervalParser;

    public DayOfWeekParser(IntegerListParser integerListParser, IntervalParser intervalParser) {
        this.integerListParser = integerListParser;
        this.intervalParser = intervalParser;
    }

    @Override
    public DayOfWeek parse(String expression) {
        if (expression.length() == 1) {
            char firstChar = expression.charAt(0);
            if (firstChar == '*') {
                return DayOfWeek.ALL_POSSIBLE_VALUES();
            }
        } else if (expression.contains(",")) {
            List<Integer> values = integerListParser.parse(expression);
            return new DayOfWeek(values);
        } else if (expression.contains("-")) {
            Interval interval = intervalParser.parse(expression);
            return DayOfWeek.createFromInterval(interval);
        }
        return new DayOfWeek(Arrays.asList(Integer.valueOf(expression)));
    }
}
