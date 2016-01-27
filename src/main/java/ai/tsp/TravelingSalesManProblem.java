package ai.tsp;


import ai.Edge;
import ai.Node;
import ai.Pair;
import ai.SearchProblem;

import java.util.*;

/**
 * Created by youngbinkim on 1/27/16.
 */
public class TravelingSalesManProblem implements SearchProblem {
    Set<Node> unvisited = new HashSet<>();
    List<Edge> edges = new ArrayList<>();
    Node firstNode;

    Map<Pair<Character, Character>, Edge> edgesMap = new HashMap<>();

    @Override
    public Node getInitialState() {
        return firstNode;
    }

    @Override
    public boolean goalTest(Node node) {
        return unvisited.isEmpty();
    }

    @Override
    public List<Node> takeActions(Node node) {
        return new ArrayList<>(unvisited);
    }

    @Override
    public int getDist(Node node, Node neighbour) {
        Pair<Character, Character> pair;
        char firstName = node.getName();
        char secondName = node.getName();
        if (firstName > secondName)
            pair = new Pair<>(secondName, firstName);
        else
            pair = new Pair<>(firstName, secondName);
        return edgesMap.get(pair).getDist();
    }

    @Override
    public void calculateF(Node neighbour, int tmpGScore) {

    }
}
