package ai;

import java.util.Comparator;

/**
 * Created by youngbinkim on 1/27/16.
 */
public class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return (int) Math.floor(o1.getFScore() - o2.getFScore());
    }
}
