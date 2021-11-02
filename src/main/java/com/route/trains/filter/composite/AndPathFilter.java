package com.route.trains.filter.composite;

import com.route.trains.filter.PathFilter;
import com.route.trains.graph.Path;

public record AndPathFilter<V>(PathFilter<V> firstFilter,
                               PathFilter<V> secondFilter) implements PathFilter<V> {

    @Override
    public boolean passFilter(final Path<V> path) {
        return firstFilter.passFilter(path) && secondFilter.passFilter(path);
    }
}
