package ai;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by youngbinkim on 1/29/16.
 */
public class MapComparator implements Comparator<City> {
    Map<City, Double> base;

    public MapComparator(Map base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with
    // equals.
    @Override
    public int compare(City a, City b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }

}
