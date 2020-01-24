package cron.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class IntegerListParserTest {

    IntegerListParser parser = new IntegerListParser();

    @Test
    public void parse_list_of_positive_integers() {
        List<Integer> values = parser.parse("1,2,3,4");
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_list_in_illegal_format() {
        parser.parse("1 2,3,4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_list_in_illegal_character() {
        parser.parse("a,2,3,4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_list_that_contains_negative_integer() {
        parser.parse("-1,2,3,4");
    }
}