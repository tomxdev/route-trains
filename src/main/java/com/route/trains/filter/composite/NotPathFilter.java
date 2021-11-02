package com.route.trains.filter.composite;

import com.route.trains.filter.PathFilter;
import com.route.trains.graph.Path;

public record NotPathFilter<V>(PathFilter<V> originalFilter) implements PathFilter<V> {

    @Override
    public boolean passFilter(final Path<V> path) {
        return !originalFilter.passFilter(path);
    }
}
