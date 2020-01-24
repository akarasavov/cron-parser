package cron.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static cron.util.ValidationUtils.checkValues;

public class DayOfMonth {
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 31;

    public final List<Integer> values;

    public static DayOfMonth ALL_POSSIBLE_VALUES() {
        List<Integer> values = IntStream.rangeClosed(MINIMUM, MAXIMUM)
                .boxed().collect(Collectors.toList());
        return new DayOfMonth(values);
    }

    public static DayOfMonth createFromInterval(Interval interval) {
        return new DayOfMonth(interval.toList());
    }

    public DayOfMonth(List<Integer> values) {
        this.values = Collections.unmodifiableList(
                checkValues(values, MINIMUM, MAXIMUM)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayOfMonth that = (DayOfMonth) o;
        return Objects.equals(values, that.values);
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
