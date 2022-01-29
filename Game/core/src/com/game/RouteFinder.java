package com.game;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// This actaully finds the route
public class RouteFinder<T extends GraphNode> {
    private final Graph<T> graph;
    private final Scorer<T> nextNodeScorer;
    private final Scorer<T> targetScorer;

    public List<T> findRoute(T from, T to) {
        // Some basic setup – our “open set” of nodes that we can consider as the next step, and a map of every node that we've visited so far and what we know about it
        Queue<RouteNode> openSet = new PriorityQueue<>();
        Map<T, RouteNode<T>> allNodes = new HashMap<>();

        RouteNode<T> start = new RouteNode<>(from, null, 0d, targetScorer.computeCost(from, to));
        openSet.add(start);
        allNodes.put(from, start);

        // Our open set initially has a single node – our start point. There is no previous node for this, there's a score of 0 to get there, and we've got an estimate of how far it is from our destination.

        // The use of a PriorityQueue for the open set means that we automatically get the best entry off of it, based on our compareTo() method from earlier.

        // Now we iterate until either we run out of nodes to look at, or the best available node is our destination:

        while (!openSet.isEmpty()) {
            RouteNode<T> next = openSet.poll();
            if (next.getCurrent().equals(to)) {
                List<T> route = new ArrayList<>();
                RouteNode<T> current = next;
                do {
                    route.add(0, current.getCurrent());
                    current = allNodes.get(current.getPrevious());
                } while (current != null);
                return route;
            }
        }

        // When we've found our destination, we can build our route by repeatedly looking at the previous node until we reach our starting point.
        // If we haven't found the destaination, work out what to do next

        graph.getConnections(next.getCurrent()).forEach(connection -> {
            RouteNode<T> nextNode = allNodes.getOrDefault(connection, new RouteNode<>(connection));
            allNodes.put(connection, nextNode);

            double newScore = next.getRouteScore() + nextNodeScorer.computeCost(next.getCurrent(), connection);
            if (newScore < nextNode.getRouteScore()) {
                nextNode.setPrevious(next.getCurrent());
                nextNode.setRouteScore(newScore);
                nextNode.setEstimatedScore(newScore + targetScorer.computeCost(connection, to));
                openSet.add(nextNode);
            }
        });

        throw new IllegalStateException("No route found");
    }
}
