package com.route.trains.command;

import com.route.trains.Commuter;

import java.io.PrintStream;
import java.util.List;

class DistanceCommand extends RouteCommand {
    public DistanceCommand(final String commandLine, final PrintStream stream) {
        super(commandLine, stream);
    }

    @Override
    protected int callCommuter(Commuter commuter, String start, String end, List<String> intermediate) {
        return commuter.routeDistance(start, end, intermediate);
    }
}
