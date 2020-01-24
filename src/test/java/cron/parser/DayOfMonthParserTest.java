package cron.parser;

import cron.model.DayOfMonth;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class DayOfMonthParserTest {

    DayOfMonthParser parser = new DayOfMonthParser(new IntegerListParser(), new IntervalParser());

    @Test
    public void parse_valid_day_of_month_expression() {
        DayOfMonth parse = parser.parse("29");
        assertEquals(List.of(29), parse.values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_invalid_day_of_month_expression() {
        parser.parse("0");
    }

    @Test
    public void parse_list_of_day_of_month() {
        DayOfMonth parse = parser.parse("1,20");
        assertEquals(Arrays.asList(1, 20), parse.values);
    }

    @Test
    public void parse_range_of_day_of_month() {
        DayOfMonth parse = parser.parse("1-20");
        List<Integer> values = IntStream.rangeClosed(1, 20)
                .boxed().collect(Collectors.toList());

        assertEquals(values, parse.values);
    }
}