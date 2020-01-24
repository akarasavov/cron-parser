package cron.parser;

import cron.model.Hours;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HoursParserTest {

    HoursParser parser = new HoursParser(new IntegerListParser(), new IntervalParser());

    @Test
    public void parse_valid_hours_expression_with_two_digits() {
        Hours parse = parser.parse("23");
        Assert.assertEquals(Arrays.asList(23), parse.values);
    }

    @Test
    public void parse_valid_hours_expression_with_one_digit() {
        Hours parse = parser.parse("2");
        Assert.assertEquals(Arrays.asList(2), parse.values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_expression_with_value_that_is_more_than_hours_range() {
        parser.parse("24");
    }

    @Test
    public void parse_valid_range_of_hours() {
        Hours parse = parser.parse("1-10");
        List<Integer> values = IntStream.rangeClosed(1, 10)
                .boxed().collect(Collectors.toList());

        Assert.assertEquals(values, parse.values);
    }

    @Test
    public void parse_valid_list_of_hours() {
        Hours parse = parser.parse("1,2,10");
        Assert.assertEquals(Arrays.asList(1,2,10), parse.values);
    }

}