package ai;

import java.util.*;

/**
 * Created by youngbinkim on 1/26/16.
 */
public class aStarSearch {
    public Node run(final SearchProblem problem) {
        Node node = problem.getInitialState();
        final PriorityQueue<Node> frontier = new PriorityQueue();
        frontier.add(node);
        final Map<Character, Integer> gScoreMap = new HashMap<>();
        final Set<Character> explored = new HashSet<>();

        while(!frontier.isEmpty()) {
            node = frontier.poll();

            if (problem.goalTest(node))
                return node;

            List<Node> neighbours = problem.takeActions(node);
            int currentGScore = gScoreMap.get(node.getName());
            gScoreMap.remove(node.getName());

            for (Node neighbour : neighbours) {
                Character nName = neighbour.getName();
                int tmpGScore = currentGScore + problem.getDist(node, neighbour);
                if (explored.contains(nName))
                    continue;
                else if (gScoreMap.containsKey(nName))
                    if (gScoreMap.get(nName) < tmpGScore)
                        continue;

                neighbour.setParent(node);
                gScoreMap.put(neighbour.getName(), tmpGScore);
                problem.calculateF(neighbour, tmpGScore);
                frontier.add(neighbour);
            }
        }

        return null;
    }
}