package com.route.trains.filter;

import com.route.trains.graph.Path;

public class RepeatedEdgePathFilter<V> implements PathFilter<V> {
    @Override
    public boolean passFilter(final Path<V> path) {
        return !path.hasRepeatedEdges();
    }
}
