package com.route.trains.command;

import com.route.trains.Commuter;

import java.io.PrintStream;

class TripsCommand extends AbstractCommand {
    private final PrintStream outputStream;

    public TripsCommand(final String commandLine, final PrintStream stream) {
        super(commandLine);
        this.outputStream = stream;
    }

    @Override
    public void execute(final Commuter commuter) {
        final String routeLine = getCommandLine().substring(7);
        final String[] commandParts = routeLine.split(",");

        final String filterCriteria = commandParts[0];
        final int filterValue = Integer.parseInt(commandParts[1]);
        final String startNode = String.valueOf(commandParts[2].charAt(0));
        final String endNode = String.valueOf(commandParts[2].charAt(2));

        int numberOfTrips = 0;

        try {
            if (filterCriteria.equalsIgnoreCase("MAX_STOPS")) {
                numberOfTrips = commuter.numberOfPathsWithMaxStops(startNode, endNode, filterValue);
            } else if (filterCriteria.equalsIgnoreCase("EXACT_STOPS")) {
                numberOfTrips = commuter.numberOfPathsWithExactStops(startNode, endNode, filterValue);
            } else if (filterCriteria.equalsIgnoreCase("MAX_DISTANCE")) {
                numberOfTrips = commuter.numberOfPathsWithMaxWeight(startNode, endNode, filterValue);
            }
            outputStream.println(numberOfTrips);
        } catch (final Exception e) {
            outputStream.println(RouteCommand.NO_ROUTE_MSG);
        }
    }
}
