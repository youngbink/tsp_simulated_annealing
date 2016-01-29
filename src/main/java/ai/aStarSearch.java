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

            if (problem.goalTest(node))
                return node;

            double currentGScore = node.getGScore(); //gScoreMap.get(node.getName());

            System.out.println("Node: " + " with score : " + currentGScore);
            System.out.println("Current City " + node.getCurrent().getName());
            node.printPath();

            List<Node> neighbours = problem.takeActions(node);


            node.setState(Node.STATE_CLOSED);
            for (Node neighbour : neighbours) {
                double tmpGScore = currentGScore + problem.getDist(node, neighbour);
                if (neighbour.getState() == Node.STATE_CLOSED) {
                    System.out.println("should not reach... closed !");
                    continue;
                } else if (neighbour.getState() == Node.STATE_OPEN) {
                    System.out.println("should not reach... open !");
                    if (neighbour.getGScore() < tmpGScore) {
                        continue;
                    }
                }

                neighbour.setGScore(tmpGScore);
                neighbour.setState(Node.STATE_OPEN);
                problem.calculateF(neighbour, tmpGScore);
                frontier.add(neighbour);
            }
        }

        return null;
    }
}