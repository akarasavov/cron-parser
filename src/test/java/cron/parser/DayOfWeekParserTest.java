package cron.parser;

import cron.model.DayOfWeek;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DayOfWeekParserTest {

    DayOfWeekParser parser = new DayOfWeekParser(new IntegerListParser(), new IntervalParser());

    @Test
    public void parse_valid_day_of_week_expression() {
        DayOfWeek parse = parser.parse("7");
        assertEquals(Arrays.asList(7), parse.values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_invalid_day_of_week_expression() {
        parser.parse("8");
    }

    @Test
    public void parse_range_of_values() {
        DayOfWeek parse = parser.parse("1-7");
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), parse.values);
    }

    @Test
    public void parse_list_of_values() {
        DayOfWeek parse = parser.parse("1,7");
        assertEquals(Arrays.asList(1, 7), parse.values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_illegal_list_of_values() {
        DayOfWeek parse = parser.parse("0,7");
        assertEquals(Arrays.asList(1, 7), parse.values);
    }


}