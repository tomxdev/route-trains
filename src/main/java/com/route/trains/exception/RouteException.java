package com.route.trains.exception;

import java.io.Serial;

public class RouteException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7100179587555243994L;
    public RouteException(final String startingVertex, final String endingVertex) {
        super("No route exists between " + startingVertex + " and " + endingVertex);
    }
}
