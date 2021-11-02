package com.route.trains.filter;

import com.route.trains.graph.Path;

public record ExactHopsPathFilter<V>(int hopsNumber) implements PathFilter<V> {

    @Override
    public boolean passFilter(final Path<V> path) {
        return path.getNumberOfHops() == hopsNumber;
    }
}
