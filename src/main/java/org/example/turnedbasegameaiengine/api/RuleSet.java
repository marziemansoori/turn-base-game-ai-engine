package org.example.turnedbasegameaiengine.api;

import org.example.turnedbasegameaiengine.game.Board;
import org.example.turnedbasegameaiengine.game.Rule;
import java.util.*;
import java.util.function.Consumer;

public class RuleSet<T extends Board> implements Iterable<Rule<T>> {

    List<Rule<T>> ruleList = new ArrayList<>();

    public void add(Rule<T> boardRule) {
        ruleList.add(boardRule);
    }

    @Override
    public Iterator<Rule<T>> iterator() {
        return ruleList.listIterator();
    }

    @Override
    public void forEach(Consumer<? super Rule<T>> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Rule<T>> spliterator() {
        return Iterable.super.spliterator();
    }
}
