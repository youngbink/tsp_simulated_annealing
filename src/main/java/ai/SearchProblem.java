package ai;

import java.util.List;

/**
 * Created by youngbinkim on 1/26/16.
 */
public interface SearchProblem {
    Node getInitialState();
    boolean goalTest(final Node node);
    List<Node> takeActions(final Node node);

    int getDist(Node node, Node neighbour);

    void calculateF(Node neighbour, int tmpGScore);
}
