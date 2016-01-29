package ai;

import java.util.List;

/**
 * Created by youngbinkim on 1/26/16.
 */
public interface SearchProblem {
    Node getInitialState();
    boolean goalTest(final Node node);
    List<Node> takeActions(final Node node);

    double getDist(Node node, Node neighbour);

    void calculateF(Node neighbour, double tmpGScore);

    void visit(Node node);
}
