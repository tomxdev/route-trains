package com.route.trains.command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public record CommandBuilder(PrintStream outputStream) {
    private static final String DISTANCE_REGEX = "DISTANCE:\\s\\D-\\D(-\\D)*";
    private static final String TRIPS_REGEX = "TRIPS:\\s(MAX_STOPS|EXACT_STOPS|MAX_DISTANCE),(\\d)+,\\D-\\D";
    private static final String GRAPH_REGEX = "GRAPH:\\s(\\D\\D\\d+)(, \\D\\D\\d+)*";
    private static final String SHORTEST_REGEX = "SHORTEST:\\s\\D-\\D";
    private static final String DURATION_REGEX = "DURATION:\\s\\w-\\w(-\\w)*";

    public List<Command> getCommandsFromFile(final File inputFile) throws IOException {
        final List<Command> commands = new ArrayList<>();
        final BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        while (reader.ready()) {
            final Command toAdd = executeLine(reader);
            if (toAdd != null) {
                commands.add(toAdd);
            }
        }
        reader.close();
        return commands;
    }

    private Command executeLine(final BufferedReader reader) throws IOException {
        Command executedCommand = null;
        final String currentLine = reader.readLine().toUpperCase();
        if (currentLine.matches(GRAPH_REGEX)) {
            executedCommand = new GraphBuilderCommand(currentLine);
        } else if (currentLine.matches(SHORTEST_REGEX)) {
            executedCommand = new ShortestDistanceCommand(currentLine, outputStream);
        } else if (currentLine.matches(TRIPS_REGEX)) {
            executedCommand = new TripsCommand(currentLine, outputStream);
        } else if (currentLine.matches(DISTANCE_REGEX)) {
            executedCommand = new DistanceCommand(currentLine, outputStream);
        } else if (currentLine.matches(DURATION_REGEX)) {
            executedCommand = new DurationCommand(currentLine, outputStream);
        } else {
            System.out.println("Line: " + currentLine + " is invalid");
        }
        return executedCommand;
    }
}
