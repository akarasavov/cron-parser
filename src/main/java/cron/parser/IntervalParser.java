package cron.parser;

import cron.model.Interval;

public class IntervalParser implements Parser<Interval> {
    @Override
    public Interval parse(String expression) {
        if (!expression.contains("-")) {
            throw new IllegalArgumentException(expression + " doesn't contains -");
        }
        String[] tokens = expression.split("-");
        if (tokens.length > 2) {
            throw new IllegalArgumentException("interval should have format a-b where a and b are numbers");
        }

        return new Interval(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
    }
}
