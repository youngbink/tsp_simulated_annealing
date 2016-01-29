package ai.tsp;


import ai.Edge;
import ai.Node;
import ai.Pair;
import ai.SearchProblem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by youngbinkim on 1/27/16.
 */
public class TravelingSalesManProblem implements SearchProblem {
    Set<Node> unvisited = new HashSet<>();
    Set<Node> visited = new HashSet<>();
    List<Edge> edges = new ArrayList<>();
    Node firstNode;

    public void initiateProblem(final String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        boolean isFirstNode = true;
        //Read File Line By Line
        try {
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                String[] tokens = strLine.split(" ");
                if (tokens.length < 3) {
                    System.out.println("Invalid tokens.. " + strLine);
                    continue;
                }

                Node node = new Node(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                if (isFirstNode) {
                    //visited.add(node);
                    firstNode = node;
                    isFirstNode = false;
                }

                unvisited.add(node);
                //}
            }
            createEdges();
        } catch (Exception e) {
            System.err.println("Exception occurred while initating the problem .. " + e);
        }
    }

    private void createEdges() {

    }

    @Override
    public Node getInitialState() {
        return firstNode;
    }

    @Override
    public boolean goalTest(final Node node) {
        return unvisited.isEmpty();
    }

    @Override
    public List<Node> takeActions(final Node node) {
        return new ArrayList<>(unvisited);
    }

    @Override
    public double getDist(final Node node, final Node neighbour) {
        return Math.sqrt(Math.pow((neighbour.getX() - node.getX()), 2) +
                Math.pow((neighbour.getY() - node.getY()), 2));
    }
    /*
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
    }*/

    @Override
    public void calculateF(final Node neighbour, final double tmpGScore) {
        neighbour.setFScore(0 + tmpGScore);
    }

    @Override
    public void visit(Node node) {
        unvisited.remove(node);
        visited.add(node);
    }
}
