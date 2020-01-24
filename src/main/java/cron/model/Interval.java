package cron.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static cron.util.ValidationUtils.checkPositive;

public class Interval {

    public final int start;
    public final int end;

    public Interval(int start, int end) {
        if (start >= end) {
            throw new IllegalArgumentException(end + " should be less than " + start);
        }
        this.start = checkPositive(start);
        this.end = checkPositive(end);
    }

    public List<Integer> toList() {
        return IntStream.rangeClosed(start, end)
                .boxed().collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return start == interval.start &&
                end == interval.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

}
