package com.route.trains.filter;

import com.route.trains.graph.Path;

public record ContainsPathFilter<V>(Path<V> objectivePath) implements PathFilter<V> {

    @Override
    public boolean passFilter(final Path<V> path) {
        return objectivePath.startsWith(path);
    }
}
