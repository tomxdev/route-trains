package com.route.trains.filter;

import com.route.trains.graph.Path;

public record MaxHopsPathFilter<V>(int maxHops) implements PathFilter<V> {

    @Override
    public boolean passFilter(final Path<V> path) {
        return path.getNumberOfHops() <= maxHops;
    }
}
