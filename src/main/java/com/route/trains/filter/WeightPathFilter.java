package com.route.trains.filter;

import com.route.trains.graph.Path;

public record WeightPathFilter<V>(int maxWeight) implements PathFilter<V> {

    @Override
    public boolean passFilter(final Path<V> path) {
        return path.getPathTotalWeight() < maxWeight;
    }
}
