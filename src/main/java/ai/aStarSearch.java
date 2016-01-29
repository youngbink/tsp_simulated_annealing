package ai;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by youngbinkim on 1/26/16.
 */
public class AStarSearch {
    public Node run(final SearchProblem problem, AtomicInteger numNodes) {
        Node node = problem.getInitialState();
        final PriorityQueue<Node> frontier = new PriorityQueue(16, new NodeComparator());
        frontier.add(node);
        //int numNodeExpanded = 0;

        while(!frontier.isEmpty()) {

            //numNodeExpanded++;

            node = frontier.poll();
/*
            //if (node.getPath().size() == 5) {
                System.out.println("fscore: " + node.getFScore());
                System.out.println("Current City " + node.getCurrent().getName());
                node.printPath();
            //}
            */

            if (problem.goalTest(node)) {
                //System.out.println("node expanded " + numNodeExpanded);
                return node;
            }

            double currentGScore = node.getGScore(); //gScoreMap.get(node.getName());




            List<Node> neighbours = problem.takeActions(node);


            node.setState(Node.STATE_CLOSED);
            for (Node neighbour : neighbours) {
                numNodes.getAndIncrement();
                double tmpGScore = currentGScore + problem.getDist(node, neighbour);
                //System.out.println(" neighbour " + neighbour.getCurrent().getName());
               // System.out.println("  g score: " + tmpGScore);
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