package cron.parser;

import cron.model.Minutes;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class MinutesParserTest {

    MinutesParser minutesParser = new MinutesParser(new IntegerListParser(), new IntervalParser());

    @Test
    public void parse_minutes_expression_with_one_asterix() {
        Minutes minutes = minutesParser.parse("*");
        List<Integer> expectValues = IntStream.rangeClosed(0, 59)
                .boxed().collect(Collectors.toList());

        assertEquals(expectValues, minutes.values);
    }

    @Test
    public void parse_minutes_expression_with_one_digit() {
        Minutes minutes = minutesParser.parse("1");
        assertEquals(Collections.singletonList(1), minutes.values);
    }

    @Test
    public void parse_minutes_expression_with_two_digits() {
        Minutes minutes = minutesParser.parse("15");
        assertEquals(Collections.singletonList(15), minutes.values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_expression_that_is_out_of_range() {
        minutesParser.parse("60");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_minutes_expression_with_negative_value() {
        minutesParser.parse("-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_minutes_expression_with_illegal_characters() {
        minutesParser.parse("q");
    }

    @Test
    public void parse_minutes_represented_with_list_of_values() {
        Minutes parse = minutesParser.parse("1,2,3");
        assertEquals(Arrays.asList(1, 2, 3), parse.values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_minutes_with_negative_value() {
        minutesParser.parse("-1,2,3");
    }

    @Test
    public void parse_valid_range_of_minutes() {
        Minutes minutes = minutesParser.parse("1-20");
        List<Integer> values = IntStream.rangeClosed(1, 20)
                .boxed().collect(Collectors.toList());

        Assert.assertEquals(values, minutes.values);
    }

}