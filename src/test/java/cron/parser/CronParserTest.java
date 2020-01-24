package cron.parser;

import cron.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CronParserTest {
    CronParser cronParser = CronParser.createCronParser();

    @Test(expected = IllegalArgumentException.class)
    public void parse_cron_expression_that_contains_more_than_6_tokens() {
        cronParser.parse("*/15 0 1,15 * 1-5 * /usr/bin/find");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_invalid_expression_with_unsupported_asterix_construction() {
        cronParser.parse("*/15 0 1,15 * 1-5 /usr/bin/find");
    }

    @Test
    public void parse_valid_expression() {
        CronCommand cronCommand = cronParser.parse("15-16 0 1,15 * 1-5 /usr/bin/find");

        List<Integer> minutes = IntStream.rangeClosed(15, 16)
                .boxed().collect(Collectors.toList());
        List<Integer> hours = List.of(0);
        List<Integer> dayOfMonth = Arrays.asList(1, 15);
        List<Integer> month = IntStream.rangeClosed(1, 12)
                .boxed().collect(Collectors.toList());
        List<Integer> dayOfWeek = IntStream.rangeClosed(1, 5)
                .boxed().collect(Collectors.toList());
        String expression = "/usr/bin/find";

        CronCommand expected = new CronCommand(new Minutes(minutes),
                new Hours(hours),
                new DayOfMonth(dayOfMonth),
                new Month(month),
                new DayOfWeek(dayOfWeek),
                expression
        );

        Assert.assertEquals(expected, cronCommand);
    }
}