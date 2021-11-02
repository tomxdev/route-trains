package com.route.trains.graph;

public interface Edge<V> {
    V getStartingVertex();
    V getEndingVertex();
    int getWeight();
}
