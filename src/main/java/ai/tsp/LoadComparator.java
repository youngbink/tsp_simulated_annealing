package ai.tsp;

import ai.Node;

import java.util.Comparator;

/**
 * Created by youngbinkim on 1/29/16.
 */
public class LoadComparator  implements Comparator<Load> {
    @Override
    public int compare(Load o1, Load o2) {
        return (int) Math.floor(o1.getDist() - o2.getDist());
    }
}