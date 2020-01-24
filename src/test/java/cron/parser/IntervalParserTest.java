package cron.parser;

import cron.model.Interval;
import org.junit.Assert;
import org.junit.Test;

public class IntervalParserTest {

    IntervalParser intervalParser = new IntervalParser();

    @Test
    public void parse_valid_interval_expression() {
        Interval interval = intervalParser.parse("1-2");
        Assert.assertEquals(new Interval(1, 2), interval);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_invalid_interval_with_negative_values() {
        intervalParser.parse("-1-2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_invalid_interval_expression_where_start_is_more_than_end() {
        intervalParser.parse("5-2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_invalid_interval_expression_where_expression_format_is_invalid() {
        intervalParser.parse("5- 2");
    }

}