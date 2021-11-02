package com.route.trains.graph;

public record DefaultEdge<V>(V startingVertex, V endingVertex, int weight) implements Edge<V> {

    public static <V> Edge<V> getWeightedEdge(final V startingVertex, final V endingVertex, final int weight) {
        return new DefaultEdge<>(startingVertex, endingVertex, weight);
    }

    @Override
    public V getStartingVertex() {
        return startingVertex;
    }

    @Override
    public V getEndingVertex() {
        return endingVertex;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "(" + startingVertex.toString() + ", " + endingVertex.toString() + "); ";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (endingVertex == null ? 0 : endingVertex.hashCode());
        result = prime * result + (startingVertex == null ? 0 : startingVertex.hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultEdge other = (DefaultEdge) obj;
        if (endingVertex == null) {
            if (other.endingVertex != null) {
                return false;
            }
        } else if (!endingVertex.equals(other.endingVertex)) {
            return false;
        }
        if (startingVertex == null) {
            return other.startingVertex == null;
        } else return startingVertex.equals(other.startingVertex);
    }
}
