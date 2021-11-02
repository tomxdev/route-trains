package com.route.trains.command;

import com.route.trains.Commuter;
import com.route.trains.graph.Graph;

class GraphBuilderCommand extends AbstractCommand {

    public GraphBuilderCommand(final String commandLine) {
        super(commandLine);
    }

    @Override
    public void execute(final Commuter commuter) {
        final String nodesLine = getCommandLine().substring(7);
        final String[] nodes = nodesLine.split(",");
        for (final String vertexEdgePair : nodes) {
            addNodes(commuter.getAllRoutes(), vertexEdgePair);
        }

    }

    private void addNodes(final Graph<String> graph, final String vertexEdgePair) {
        final String trimmedPair = vertexEdgePair.trim();
        final String from = String.valueOf(trimmedPair.charAt(0));
        final String to = String.valueOf(trimmedPair.charAt(1));
        final int weight = Integer.parseInt(trimmedPair.substring(2));
        graph.addVertex(from);
        graph.addVertex(to);
        graph.addEdge(from, to, weight);
    }

}
