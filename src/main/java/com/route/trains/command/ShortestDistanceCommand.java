package com.route.trains.command;

import com.route.trains.Commuter;
import com.route.trains.exception.RouteException;

import java.io.PrintStream;

class ShortestDistanceCommand extends AbstractCommand {
    private final PrintStream outputStream;

    public ShortestDistanceCommand(final String commandLine, final PrintStream stream) {
        super(commandLine);
        this.outputStream = stream;
    }

    @Override
    public void execute(final Commuter commuter) {
        final String routeLine = getCommandLine().substring(10);
        final String from = String.valueOf(routeLine.charAt(0));
        final String to = String.valueOf(routeLine.charAt(2));
        try {
            outputStream.println(commuter.shortestDistance(from, to));
        } catch (final RouteException e) {
            outputStream.println(RouteCommand.NO_ROUTE_MSG);
        }
    }
}
