package cron.parser;

import cron.model.*;

public class CronParser implements Parser<CronCommand> {

    private final Parser<Minutes> minutesParser;
    private final Parser<Hours> hoursParser;
    private final Parser<DayOfMonth> dayOfMonthParser;
    private final Parser<Month> monthParser;
    private final Parser<DayOfWeek> dayOfWeekParser;

    public CronParser(Parser<Minutes> minutesParser,
                      Parser<Hours> hoursParser,
                      Parser<DayOfMonth> dayOfMonthParser,
                      Parser<Month> monthParser,
                      Parser<DayOfWeek> dayOfWeekParser) {
        this.minutesParser = minutesParser;
        this.hoursParser = hoursParser;
        this.dayOfMonthParser = dayOfMonthParser;
        this.monthParser = monthParser;
        this.dayOfWeekParser = dayOfWeekParser;
    }

    public static CronParser createCronParser() {
        IntervalParser intervalParser = new IntervalParser();
        IntegerListParser integerListParser = new IntegerListParser();
        return new CronParser(new MinutesParser(integerListParser, intervalParser),
                new HoursParser(integerListParser, intervalParser),
                new DayOfMonthParser(integerListParser, intervalParser),
                new MonthParser(integerListParser, intervalParser),
                new DayOfWeekParser(integerListParser, intervalParser));
    }

    @Override
    public CronCommand parse(String expression) {
        String[] tokens = expression.split(" ");
        if (tokens.length > 6) {
            throw new IllegalArgumentException("Cron expression contains more then 6 tokens");
        }
        final Minutes minutes = minutesParser.parse(tokens[0]);
        final Hours hours = hoursParser.parse(tokens[1]);
        final DayOfMonth dayOfMonth = dayOfMonthParser.parse(tokens[2]);
        final Month month = monthParser.parse(tokens[3]);
        final DayOfWeek dayOfWeek = dayOfWeekParser.parse(tokens[4]);
        final String command = validateCommand(tokens[5]);
        return new CronCommand(minutes,
                hours,
                dayOfMonth,
                month,
                dayOfWeek,
                command
        );
    }

    public String validateCommand(String command) {
        if (command.length() == 0) {
            throw new IllegalArgumentException("Command is illegal");
        }
        return command;
    }
}
