package cron.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static cron.util.ValidationUtils.checkValues;

public class DayOfWeek {
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 7;

    public final List<Integer> values;

    public static DayOfWeek ALL_POSSIBLE_VALUES() {
        List<Integer> values = IntStream.rangeClosed(MINIMUM, MAXIMUM)
                .boxed().collect(Collectors.toList());
        return new DayOfWeek(values);
    }

    public static DayOfWeek createFromInterval(Interval interval) {
        return new DayOfWeek(interval.toList());
    }

    public DayOfWeek(List<Integer> values) {
        this.values = Collections.unmodifiableList(
                checkValues(values, MINIMUM, MAXIMUM)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayOfWeek dayOfWeek = (DayOfWeek) o;
        return Objects.equals(values, dayOfWeek.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return values.stream()
                .map(v -> "" + v)
                .collect(Collectors.joining(" "));
    }
}
