package ai.tsp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by youngbinkim on 1/28/16.
 */
public class City {
    String name;
    int x;
    int y;
    Map<City, Double> dMap = new HashMap<>(); // contains (neighbour, distance to neighbour)

    public City (String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addCityToMap(City neighbour, Double dist) {
        dMap.put(neighbour, dist);
    }

    public Map<City, Double> getdMap() {
        return dMap;
    }

    public void setdMap(Map<City, Double> dMap) {
        this.dMap = dMap;
    }

    public String getName() {
        return name;
    }
}
