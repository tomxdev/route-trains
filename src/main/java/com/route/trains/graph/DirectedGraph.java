package com.route.trains.graph;

import com.route.trains.exception.RouteException;
import com.route.trains.filter.PathFilter;

import java.util.*;

public class DirectedGraph<V> implements Graph<V> {
    private final Map<V, Set<Edge<V>>> edges = new HashMap<>();

    @Override
    public boolean addVertex(final V vertex) {
        assertVertexNotNull(vertex);
        if (!edges.containsKey(vertex)) {
            edges.put(vertex, new LinkedHashSet<>());
            return true;
        }
        return false;
    }

    private void assertVertexNotNull(final V vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Vertex can not be null");
        }
    }

    @Override
    public boolean addEdge(final V from, final V to, final int weight) {
        assertVertexExists(from);
        assertVertexExists(to);

        final Edge<V> newEdge = DefaultEdge.getWeightedEdge(from, to, weight);

        final Set<Edge<V>> sourceEdges = edges.get(from);
        sourceEdges.remove(newEdge);
        return edges.get(from).add(newEdge);
    }

    private void assertVertexExists(final V vertex) {
        if (!edges.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex " + vertex.toString() + " does not exist");
        }
    }

    @Override
    public Edge<V> getEdge(final V from, final V to) {
        assertVertexExists(from);
        assertVertexExists(to);

        final Set<Edge<V>> startingVertexEdges = edges.get(from);
        for (final Edge<V> eachEdge : startingVertexEdges) {
            if (eachEdge.getEndingVertex().equals(to)) {
                return eachEdge;
            }
        }
        return null;
    }

    @Override
    public List<Path<V>> getAllPaths(final V startingNode, final V endingNode, final PathFilter<V> filter) {
        assertVertexExists(startingNode);
        assertVertexExists(endingNode);

        final List<Path<V>> paths = new ArrayList<>();
        for (final Edge<V> each : edges.get(startingNode)) {
            final Path<V> path = GraphPath.emptyPath();
            path.addEdge(each);
            paths.addAll(search(path, filter, endingNode));
        }

        if (paths.isEmpty()) {
            throw new RouteException(startingNode.toString(), endingNode.toString());
        }
        return paths;
    }

    private List<Path<V>> search(final Path<V> path, final PathFilter<V> filter, final V end) {
        final List<Path<V>> paths = new ArrayList<>();
        if (filter.passFilter(path)) {
            if (hasReachedGoal(path, end)) {
                paths.add(GraphPath.copyPath(path));
            }
            for (final Edge<V> each : edges.get(path.getLastNode())) {
                path.addEdge(each);
                paths.addAll(search(path, filter, end));
            }

        }
        path.removeLastEdge();
        return paths;
    }

    private boolean hasReachedGoal(final Path<V> path, final V end) {
        return path.getLastNode().equals(end);
    }

    @Override
    public Set<V> getAllVertex() {
        return Collections.unmodifiableSet(edges.keySet());
    }
}
