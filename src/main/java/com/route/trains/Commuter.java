package com.route.trains;

import com.route.trains.graph.Graph;

import java.util.List;

public interface Commuter {

    int routeDistance(String startingCity, String destinationCity, List<String> intermediateCities);
    int numberOfPathsWithMaxStops(String startingCity, String destinationCity, int stops);
    int numberOfPathsWithMaxWeight(String startingCity, String destinationCity, int weight);
    int numberOfPathsWithExactStops(String startingCity, String destinationCity, int stops);
    int shortestDistance(String startingCity, String destinationCity);
    Graph<String> getAllRoutes();
    int routeDuration(String startingCity, String endCity, List<String> intermediateCities);

}
