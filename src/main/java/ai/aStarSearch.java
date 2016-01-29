package ai;

import java.util.*;

/**
 * Created by youngbinkim on 1/26/16.
 */
public class AStarSearch {
    public Node run(final SearchProblem problem) {
        Node node = problem.getInitialState();
        final PriorityQueue<Node> frontier = new PriorityQueue(16, new NodeComparator());
        frontier.add(node);

        while(!frontier.isEmpty()) {
            node = frontier.poll();
            problem.visit(node);

            if (problem.goalTest(node))
                return node;

            List<Node> neighbours = problem.takeActions(node);
            double currentGScore = node.getGScore(); //gScoreMap.get(node.getName());
            System.out.println("Node: " + node.getName() + " with score : " + currentGScore);
            node.setState(Node.STATE_CLOSED);
            for (Node neighbour : neighbours) {
                double tmpGScore = currentGScore + problem.getDist(node, neighbour);
                if (node.getState() == Node.STATE_CLOSED)
                    continue;
                else if (node.getState() == Node.STATE_OPEN)
                    if (neighbour.getGScore() < tmpGScore)
                        continue;

                neighbour.setGScore(tmpGScore);
                neighbour.setState(Node.STATE_OPEN);
                problem.calculateF(neighbour, tmpGScore);
                frontier.add(neighbour);
            }
        }

        return null;
    }
}