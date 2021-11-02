package com.route.trains;

import com.route.trains.filter.*;
import com.route.trains.graph.DefaultEdge;
import com.route.trains.graph.Graph;
import com.route.trains.graph.GraphPath;
import com.route.trains.graph.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CommuterImpl implements Commuter {
    private final Graph<String> routes;

    public CommuterImpl(final Graph<String> routes) {
        super();
        this.routes = routes;
    }

    @Override
    public int numberOfPathsWithExactStops(final String startingCity, final String destinationCity, final int stops) {

        final List<Path<String>> paths = routes.getAllPaths(startingCity, destinationCity, new MaxHopsPathFilter<>(stops));
        final List<Path<String>> exactPaths = new ArrayList<>();

        final PathFilter<String> exactFilter = new ExactHopsPathFilter<>(stops);
        for (final Path<String> each : paths) {
            if (exactFilter.passFilter(each)) {
                exactPaths.add(each);
            }
        }
        return exactPaths.size();
    }

    @Override
    public int numberOfPathsWithMaxStops(final String startingCity, final String destinationCity, final int stops) {
        return routes.getAllPaths(startingCity, destinationCity, new MaxHopsPathFilter<>(stops)).size();
    }

    @Override
    public int numberOfPathsWithMaxWeight(final String startingCity, final String destinationCity, final int weight) {
        return routes.getAllPaths(startingCity, destinationCity, new WeightPathFilter<>(weight)).size();
    }

    @Override
    public int shortestDistance(final String startingCity, final String destinationCity) {
        final List<Path<String>> allPaths = routes.getAllPaths(startingCity, destinationCity,
                new RepeatedEdgePathFilter<>());
        return Collections.min(allPaths).getPathTotalWeight();
    }

    @Override
    public int routeDistance(final String startingCity, final String destinationCity, final List<String> intermediateNodes) {
        final Path<String> objectivePath = createPath(startingCity, destinationCity, intermediateNodes);
        final List<Path<String>> allPaths = routes.getAllPaths(startingCity, destinationCity, new ContainsPathFilter<>(
                objectivePath));

        return allPaths.get(0).getPathTotalWeight();
    }

    private Path<String> createPath(final String from, final String to, final List<String> intermediateNodes) {
        final Path<String> resultingPath = GraphPath.emptyPath();
        String currentNode = from;
        if (intermediateNodes != null) {
            for (final String eachIntermediate : intermediateNodes) {
                resultingPath.addEdge(DefaultEdge.getWeightedEdge(currentNode, eachIntermediate, 0));
                currentNode = eachIntermediate;
            }
        }
        resultingPath.addEdge(DefaultEdge.getWeightedEdge(currentNode, to, 0));
        return resultingPath;
    }

    @Override
    public Graph<String> getAllRoutes() {
        return routes;
    }

    @Override
    public int routeDuration(String startingCity, String endCity, List<String> intermediateCities) {
        return  routeDistance(startingCity, endCity, intermediateCities) + 2 * intermediateCities.size();
    }
}
