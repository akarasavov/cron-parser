package cron.parser;

public interface Parser<E> {

    E parse(String expression);
}
