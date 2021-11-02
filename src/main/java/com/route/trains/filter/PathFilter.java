package com.route.trains.filter;

import com.route.trains.graph.Path;

public interface PathFilter<V> {
    boolean passFilter(final Path<V> path);

}
