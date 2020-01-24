package cron.model;

import java.util.Objects;

public class CronCommand {

    public final Minutes minutes;
    public final Hours hours;
    public final DayOfMonth dayOfMonth;
    public final Month month;
    public final DayOfWeek dayOfWeek;
    public final String command;

    public CronCommand(Minutes minutes,
                       Hours hours,
                       DayOfMonth dayOfMonth,
                       Month month,
                       DayOfWeek dayOfWeek,
                       String command) {
        this.minutes = minutes;
        this.hours = hours;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.command = command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CronCommand that = (CronCommand) o;
        return Objects.equals(minutes, that.minutes) &&
                Objects.equals(hours, that.hours) &&
                Objects.equals(dayOfMonth, that.dayOfMonth) &&
                Objects.equals(month, that.month) &&
                Objects.equals(dayOfWeek, that.dayOfWeek) &&
                Objects.equals(command, that.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minutes, hours, dayOfMonth, month, dayOfWeek, command);
    }

    @Override
    public String toString() {
        return "minute        " + minutes + "\n" +
                "hour          " + hours + "\n" +
                "day of month  " + dayOfMonth + "\n" +
                "month         " + month + "\n" +
                "day of week   " + dayOfWeek + "\n" +
                "command       " + command + "\n";

    }
}
