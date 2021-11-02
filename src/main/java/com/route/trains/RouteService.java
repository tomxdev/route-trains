package com.route.trains;

import com.route.trains.command.Command;
import com.route.trains.command.CommandBuilder;
import com.route.trains.graph.GraphBuilder;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class RouteService {

    public static void main(final String[] args) throws Exception {
        final Commuter commuter = new CommuterImpl(GraphBuilder.getEmptyGraph());
        File inputFile;
        if (args.length == 1) {
            inputFile = new File(args[0]);
        } else {
            inputFile = new File(Objects.requireNonNull(RouteService.class.getResource("/input.txt")).toURI());
        }

        final List<Command> inputCommands = new CommandBuilder(System.out).getCommandsFromFile(inputFile);
        for (final Command eachCommand : inputCommands) {
            eachCommand.execute(commuter);
        }
    }
}
