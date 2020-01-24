package cron.parser;

import cron.model.Month;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MonthParserTest {

    MonthParser parser = new MonthParser(new IntegerListParser(), new IntervalParser());

    @Test
    public void parse_valid_month_expression() {
        Month parse = parser.parse("11");
        Assert.assertEquals(Arrays.asList(11), parse.values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_illegal_month_expression() {
        Month parse = parser.parse("13");
    }

    @Test
    public void parse_expression_with_list_of_values() {
        Month parse = parser.parse("1,5,3");
        Assert.assertEquals(Arrays.asList(1, 5, 3), parse.values);
    }

    @Test
    public void parse_expression_with_range_of_values() {
        Month parse = parser.parse("1-3");
        Assert.assertEquals(Arrays.asList(1,2,3), parse.values);
    }
}