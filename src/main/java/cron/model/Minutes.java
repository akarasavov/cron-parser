package cron.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static cron.util.ValidationUtils.checkValues;

public class Minutes {
    private static final int MINIMUM = 0;
    private static final int MAXIMUM = 59;

    public final List<Integer> values;

    public static Minutes ALL_POSSIBLE_VALUES() {
        List<Integer> values = IntStream.rangeClosed(MINIMUM, MAXIMUM)
                .boxed().collect(Collectors.toList());
        return new Minutes(values);
    }

    public static Minutes createFromInterval(Interval interval) {
        return new Minutes(interval.toList());
    }

    public Minutes(List<Integer> values) {
        this.values = Collections.unmodifiableList(
                checkValues(values, MINIMUM, MAXIMUM)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Minutes minutes = (Minutes) o;
        return Objects.equals(values, minutes.values);
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
